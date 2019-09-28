package com.lxf.eye.agent.listener;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry.*;
import com.alibaba.otter.canal.protocol.Message;
import com.lxf.eye.agent.common.CanalMysqlEntry;
import com.lxf.eye.agent.common.RabbitConfig;
import com.lxf.eye.common.domain.RabbitMqQueueConfig;
import com.xxl.mq.client.message.XxlMqMessage;
import com.xxl.mq.client.producer.XxlMqProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class CanalListener {
    private static final Logger logger = LoggerFactory.getLogger(CanalListener.class);
    @Resource
    private CanalConnector canalConnector;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private String topic="canal";

    @PostConstruct
    public void listen() {
        canalConnector.connect();
        canalConnector.subscribe("eye_order\\..*");
        canalConnector.rollback();
        while (true) {
            logger.info("canal Listen.......");
            int batchSize = 1000;
            Message message = canalConnector.getWithoutAck(batchSize); // 获取指定数量的数据
            long batchId = message.getId();
            int size = message.getEntries().size();
            if (batchId == -1 || size == 0) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                }
            } else {
                List<CanalMysqlEntry> canalMysqlEntries = null;
                try {
                    canalMysqlEntries = parseEntry(message.getEntries());
                    canalMysqlEntries.stream().forEach(canalMysqlEntry -> {
                        XxlMqProducer.produce(new XxlMqMessage(topic, canalMysqlEntry.toString()));
                        rabbitTemplate.convertAndSend(RabbitMqQueueConfig.STRING, canalMysqlEntry.toString());
                        logger.info("放入队列成功：{}",canalMysqlEntry.toString());
                    });
                    canalConnector.ack(batchId); // 提交确认
                } catch (Exception e) {
                    canalConnector.rollback(batchId); // 处理失败, 回滚数据
                    e.printStackTrace();
                }

            }
        }
    }

    private List<CanalMysqlEntry> parseEntry(List<Entry> entrys) {
        List<CanalMysqlEntry> canalMysqlEntrys=new ArrayList<>();
        for (Entry entry : entrys) {
            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                continue;
            }
            RowChange rowChage = null;
            try {
                rowChage = RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }

            EventType eventType = rowChage.getEventType();
            // 可以获取到数据库实例名称、日志文件、当前操作的表以及执行的增删改查的操作
            String logFileName = entry.getHeader().getLogfileName();
            long logFileOffset = entry.getHeader().getLogfileOffset();
            String dbName = entry.getHeader().getSchemaName();
            String tableName = entry.getHeader().getTableName();
            logger.info(String.format("=======&gt; binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    logFileName, logFileOffset,
                    dbName, tableName,
                    eventType));

            for (RowData rowData : rowChage.getRowDatasList()) {
                CanalMysqlEntry canalMysqlEntry = new CanalMysqlEntry();
                canalMysqlEntry.setDataBaseName(dbName);
                canalMysqlEntry.setTableName(tableName);
                canalMysqlEntry.setType(eventType.name());
                Map<String, Object> dataBefore = new HashMap<>();
                Map<String, Object> dataAfter = new HashMap<>();
                if (eventType == EventType.DELETE) {
                    // 删除
                    dataAfter = printColumn(rowData.getBeforeColumnsList());
                } else if (eventType == EventType.INSERT) {
                    // 新增
                    dataAfter = printColumn(rowData.getAfterColumnsList());
                } else {
                    dataBefore = printColumn(rowData.getBeforeColumnsList());
                    dataAfter = printColumn(rowData.getAfterColumnsList());
                }
                canalMysqlEntry.setDataAfter(dataAfter);
                canalMysqlEntry.setDataBefore(dataBefore);
                System.out.println(canalMysqlEntry.toString());
                canalMysqlEntrys.add(canalMysqlEntry);
            }
        }
        return canalMysqlEntrys;
    }

    private static Map<String, Object> printColumn(List<Column> columns) {
        Map<String, Object> map = new HashMap<>();
        for (Column column : columns) {
            String str = column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated();
            map.put(column.getName(), column.getValue());
        }
        return map;
    }
}

package com.lxf.eye.show.listener;

import com.xxl.mq.client.consumer.IMqConsumer;
import com.xxl.mq.client.consumer.MqResult;
import com.xxl.mq.client.consumer.annotation.MqConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@MqConsumer(topic = "topic_1")
@Service
public class XXLMqComsumer  implements IMqConsumer {
    private Logger logger = LoggerFactory.getLogger(XXLMqComsumer.class);
    @Override
    public MqResult consume(String data) throws Exception {
        logger.info("[XXLMqComsumer] 消费一条消息:{}", data);
        return MqResult.SUCCESS;
    }
}

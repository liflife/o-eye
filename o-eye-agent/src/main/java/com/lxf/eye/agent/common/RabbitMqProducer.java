package com.lxf.eye.agent.common;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
public class RabbitMqProducer {
//    private static final String QUEUE_NAME = "queue.test";
    private static final String QUEUE_NAME = "canal.test";
    private static Connection connection;

    private static void init() {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("192.168.199.101");
            connectionFactory.setPort(5672);
            connectionFactory.setConnectionTimeout(6000);
            connectionFactory.setVirtualHost("/");
            connectionFactory.setUsername("guest");
            connectionFactory.setPassword("guest");
//        生成Connection & Channel
            connection = connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void sendMsg(String msg) {
        if(connection==null){
            init();
        }
        try {
            //创建一个通道
            Channel channel = connection.createChannel();
            //声明队列
            //queueDeclare第一个参数表示队列名称、
            // 第二个参数为是否持久化（true表示是，队列将在服务器重启时生存）、
            // 第三个参数为是否是独占队列（创建者可以使用的私有队列，断开后自动删除）、
            // 第四个参数为当所有消费者客户端连接断开时是否自动删除队列、第五个参数为队列的其他参数
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //发送消息到队列
            //basicPublish第一个参数为交换机名称、
            // 第二个参数为队列映射的路由key、
            // 第三个参数为消息的其他属性、
            // 第四个参数为发送信息的主体
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes("UTF-8"));
            System.out.println("Producer Send: '" + msg + "'");
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
    private static void close(){
        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        for(int i=0;i< 100;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            RabbitMqProducer.sendMsg("hello:"+i+">>"+System.currentTimeMillis());
        }
        RabbitMqProducer.close();
    }
}

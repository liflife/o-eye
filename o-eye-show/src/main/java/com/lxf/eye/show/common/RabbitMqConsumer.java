package com.lxf.eye.show.common;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author: create by xingfeng.luo
 * @version: v1.0
 * @description: RabbitMqConsumer
 * @date:2019/9/5
 **/
public class RabbitMqConsumer {
    private static final String QUEUE_NAME = "queue.test";
    private static Connection connection;

    private static void init() {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("192.168.199.101");
            connectionFactory.setPort(5672);
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

    public static void recvMsg() {
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
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            System.out.println("Customer Waiting Received messages");
            Consumer consumer = new DefaultConsumer(channel){
                /**
                 * envelope主要存放生产者相关信息（比如交换机、路由key等）body是消息实体。
                 * @throws IOException
                 */
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("Customer Received '" + message + "'");
                }
            };
            //自动回复队列应答 -- RabbitMQ中的消息确认机制
            channel.basicConsume(QUEUE_NAME,true, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        RabbitMqConsumer.recvMsg();

    }
}

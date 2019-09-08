package com.lxf.eye.agent.common;

import com.lxf.eye.common.domain.RabbitMqQueueConfig;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {



    /**
     * 定义string队列
     *
     * @return
     */
    @Bean
    public Queue string() {
        return new Queue(RabbitMqQueueConfig.STRING);
    }
}

package com.lxf.eye.agent;


import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import tk.mybatis.spring.annotation.MapperScan;

import java.net.InetSocketAddress;


@SpringBootApplication
@MapperScan(basePackages = "com.lxf.o.eye.mapper")
@ComponentScan({"com.lxf.eye.agent"})
@PropertySource("xxlmq.properties")
public class OEyeAgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(OEyeAgentApplication.class, args);
    }
    @Bean
    public CanalConnector getCanalCanalConnector(){
        // 创建链接
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("192.168.199.101"/*AddressUtils.getHostIp()*/,
                11111), "example", "canal", "canal");
        return connector;
    }
}

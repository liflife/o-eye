package com.lxf.eye.order;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan(basePackages = "com.lxf.eye.order.mapper")
@ComponentScan({"com.lxf.eye.order","com.lxf.eye.common.service"})
@EnableScheduling
public class OEyeOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OEyeOrderApplication.class, args);
    }
}

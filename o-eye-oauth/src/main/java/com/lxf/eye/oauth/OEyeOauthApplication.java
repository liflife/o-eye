package com.lxf.eye.oauth;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan(basePackages = "com.lxf.eye.show.mapper")
@ComponentScan({"com.lxf.eye.common.service","com.lxf.eye.oauth"})
@EnableScheduling
//@PropertySource("xxlmq.properties")
public class OEyeOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OEyeOauthApplication.class, args);
    }

}

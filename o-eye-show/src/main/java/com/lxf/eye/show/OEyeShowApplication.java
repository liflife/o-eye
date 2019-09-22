package com.lxf.eye.show;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan(basePackages = "com.lxf.eye.show.mapper")
@ComponentScan({"com.lxf.eye.common.service"})
@EnableScheduling
public class OEyeShowApplication {

    public static void main(String[] args) {
        SpringApplication.run(OEyeShowApplication.class, args);
    }

}

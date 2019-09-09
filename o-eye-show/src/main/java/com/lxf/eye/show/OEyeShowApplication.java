package com.lxf.eye.show;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan(basePackages = "com.lxf.eye.show.mapper")
@ComponentScan({"com.lxf.eye.show"})
public class OEyeShowApplication {

    public static void main(String[] args) {
        SpringApplication.run(OEyeShowApplication.class, args);
    }

}

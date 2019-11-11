package com.springboot.dubbo.tcc.privoder;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
@MapperScan(basePackages = "com.springboot.dubbo.tcc.privoder.mapper")
public class PrivoderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrivoderApplication.class, args);

    }
}

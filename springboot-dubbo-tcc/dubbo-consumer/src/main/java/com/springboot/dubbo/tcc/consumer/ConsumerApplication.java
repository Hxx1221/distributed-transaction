package com.springboot.dubbo.tcc.consumer;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

import com.springboot.dubbo.tcc.consumer.transaction.TransactionConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDubboConfiguration
public class ConsumerApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext run =
                SpringApplication.run(ConsumerApplication.class, args);

//        QuickstartConsumer quickstartConsumer = (QuickstartConsumer)run.getBean("quickstartConsumer");

//        quickstartConsumer.sendMessage("童鞋们都能找到一个百万年薪的工作");

        // 测试分布式事务使用
        TransactionConsumer transactionConsumer = (TransactionConsumer) run.getBean("transactionConsumer");
        transactionConsumer.sendMessage("1,2,2 3");
        // 1,2,3   4,5

    }
}

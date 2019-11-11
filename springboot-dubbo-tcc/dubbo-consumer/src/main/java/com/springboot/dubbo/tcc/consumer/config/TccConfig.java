package com.springboot.dubbo.tcc.consumer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = {"classpath:tcc-transaction.xml","classpath:tcc-transaction-dubbo.xml"})
public class TccConfig {
}
package com.Jason.orderservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = {"com.Jason"})//为了扫描到common base里的自动填充
@MapperScan("com.Jason.orderservice.mapper")
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.Jason.orderservice.client"})
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}

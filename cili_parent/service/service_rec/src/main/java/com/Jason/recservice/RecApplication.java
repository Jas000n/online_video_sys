package com.Jason.recservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient//nacos注册
@EnableFeignClients//nacos调用其他服务
@ComponentScan(basePackages = {"com.Jason"})//为了扫描到common_base里的自动填充
public class RecApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecApplication.class,args);
    }
}

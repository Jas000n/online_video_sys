package com.Jason.statistic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling //开启定时任务
@ComponentScan(basePackages = {"com.Jason"})//为了扫描到common base里的自动填充
@MapperScan("com.Jason.statistic.mapper")
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class StatisticApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticApplication.class,args);
    }
}

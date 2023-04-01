package com.Jason.cmsservice;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.Jason"}) //指定扫描位置
@MapperScan("com.Jason.cmsservice.mapper")

public class CmsApplication {
    public static void main(String[] args) {

        SpringApplication.run(CmsApplication.class, args);

    }

}
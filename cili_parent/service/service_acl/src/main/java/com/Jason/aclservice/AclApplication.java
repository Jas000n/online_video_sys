package com.Jason.aclservice;




import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.Jason"}) //指定扫描位置
@MapperScan("com.Jason.aclservice.mapper")

public class AclApplication {
    public static void main(String[] args) {

        SpringApplication.run(AclApplication.class, args);

    }

}
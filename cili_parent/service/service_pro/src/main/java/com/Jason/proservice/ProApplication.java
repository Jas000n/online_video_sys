package com.Jason.proservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * author  jas0n
 * date    2023/2/28
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.Jason"})//为了扫描到common_base里的自动填充
public class ProApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProApplication.class,args);
    }
}

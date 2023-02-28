package com.Jason.proservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * author  jas0n
 * date    2023/2/28
 */
@Configuration
@MapperScan("com.Jason.proservice.mapper")
public class ProConfig {
}

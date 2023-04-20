package com.Jason.orderservice.mapper;

import com.Jason.orderservice.entity.PayLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface PayLogMapper extends BaseMapper<PayLog> {
}

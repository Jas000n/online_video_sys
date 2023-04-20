package com.Jason.orderservice.mapper;

import com.Jason.orderservice.entity.Oorder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface OrderMapper extends BaseMapper<Oorder> {
}

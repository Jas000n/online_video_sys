package com.Jason.statistic.mapper;

import com.Jason.statistic.entity.StatisticsDaily;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface StatisticMapper extends BaseMapper<StatisticsDaily> {
}

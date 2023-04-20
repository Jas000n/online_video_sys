package com.Jason.statistic.service;

import com.Jason.statistic.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface StatisticService extends IService<StatisticsDaily> {
    //用于获取统计数据的折线图,包含起始日期,结束日期,查询的类型
    Map<String, Object> getChartData(String begin, String end, String type);
}

package com.Jason.orderservice.service;

import com.Jason.orderservice.entity.Oorder;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OrderService extends IService<Oorder> {
    //创建订单返回订单号
    String createOrder(String videoId, String memberIdByJwtToken);
}

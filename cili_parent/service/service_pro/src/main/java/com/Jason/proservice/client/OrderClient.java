package com.Jason.proservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Component
@FeignClient(value = "service-order")
public interface OrderClient {

    //查询订单信息,确定是否购买
    @GetMapping("/order/order/isBuyVideo/{memberId}/{id}")
    public boolean isBuyVideo(@PathVariable(value="memberId") String memberid, @PathVariable(value = "id") String id);
}
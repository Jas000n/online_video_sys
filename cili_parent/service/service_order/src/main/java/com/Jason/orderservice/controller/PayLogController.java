package com.Jason.orderservice.controller;

import com.Jason.common.utils.R;
import com.Jason.orderservice.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequestMapping("/order/log")
//@CrossOrigin
public class PayLogController {
    @Autowired
    private PayLogService payService;
        /**
         * 生成二维码
         * @return
         */
    @GetMapping("/createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo) {
        Map map = payService.createNative(orderNo);
        return R.ok().data(map);

    }

    @GetMapping("/queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo) {

        //调用查询接口
        Map<String, String> map = payService.queryPayStatus(orderNo);

        if (map == null) {//出错
            return R.error().message("支付出错");
        }
        System.out.println("trade state!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
        System.out.println(map.get("trade_state"));
        System.out.println("end trade state!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
        if (map.get("trade_state").equals("SUCCESS")) {//如果成功
            //更改订单状态
            payService.updateOrderStatus(map);
            return R.ok().message("支付成功");

        }

        return R.ok().code(25000).message("支付中");

    }
}
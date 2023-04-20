package com.Jason.orderservice.controller;

import com.Jason.common.utils.JwtUtils;
import com.Jason.common.utils.R;
import com.Jason.orderservice.entity.Oorder;
import com.Jason.orderservice.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("order/order")
//@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    //根据影视id创建订单
    @PostMapping("createOrder/{videoId}")
    public R createOrder(@PathVariable String videoId, HttpServletRequest httpServletRequest){
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(httpServletRequest);


        //创建订单返回订单号
        String orderNo = orderService.createOrder(videoId,memberIdByJwtToken);

        return R.ok().data("orderId",orderNo);
    }

    //根据id查询订单信息
    //注意这里用OrderNo查询,不是id查询
    @GetMapping("getOrderByNo/{No}")
    public R getOrderByNo(@PathVariable String No){
        QueryWrapper<Oorder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",No);
        Oorder one = orderService.getOne(queryWrapper);
        return R.ok().data("item",one);
    }

    //是否购买影视
    @GetMapping("isBuyVideo/{memberId}/{id}")
    public boolean isBuyVideo(@PathVariable String memberId,
                               @PathVariable String id) {
        //订单状态是1表示支付成功
        System.out.println("执行");
        if (memberId == null){
            System.out.println("没有登录,直接返回");
            return false;
        }
        int count = orderService.count(new QueryWrapper<Oorder>().eq("member_id", memberId).eq("video_id", id).eq("status", 1));
        if(count>0) {
            return true;
        } else {
            return false;
        }
    }
}

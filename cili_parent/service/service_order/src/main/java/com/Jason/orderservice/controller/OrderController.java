package com.Jason.orderservice.controller;

import com.Jason.common.utils.JwtUtils;
import com.Jason.common.utils.R;
import com.Jason.orderservice.entity.Oorder;
import com.Jason.orderservice.service.OrderService;
import com.Jason.orderservice.vo.OrderQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    //获取全部订单
    @GetMapping("getAll")
    public R getAll(){
        List<Oorder> list = orderService.list(null);

        return R.ok().data("list",list);
    }
    //条件查询带分页的方法
    @PostMapping("pageOrderCondition/{current}/{limit}")
    public R pageOrderCondition(@PathVariable long current, @PathVariable long limit,
                                   @RequestBody(required = false) OrderQuery orderQuery){
        System.out.println("huoqu !!!!");
        //创建page对象
        Page<Oorder> pageOrder = new Page<>(current,limit);
        //构建条件
        QueryWrapper<Oorder> wrapper = new QueryWrapper<>();
        //多条件组合查询
        //动态sql：判断条件是否唯恐，不为空则拼接条件
        String nickName = orderQuery.getNick_name();
        String videoTitle = orderQuery.getVideo_title();

        if(!StringUtils.isEmpty(nickName)){
            wrapper.like("nickname",nickName);
        }

        if(!StringUtils.isEmpty(videoTitle)){
            wrapper.le("video_title",videoTitle);
        }
        //排序
        wrapper.orderByDesc("gmt_create");

        orderService.page(pageOrder,wrapper);
        //取值，返回结果
        long total = pageOrder.getTotal();//总记录数
        List<Oorder> records = pageOrder.getRecords();//数据list集合

        return R.ok().data("total",total).data("rows",records);
    }

    //根据id，逻辑删除订单
    @DeleteMapping("/delete/{id}")
    public R deleteByID(@PathVariable String id){
        boolean b = orderService.removeById(id);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

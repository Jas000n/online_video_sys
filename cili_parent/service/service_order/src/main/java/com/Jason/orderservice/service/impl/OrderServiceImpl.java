package com.Jason.orderservice.service.impl;

import com.Jason.common.utils.vo.UCENTERMEMBER;
import com.Jason.common.utils.vo.videoInfoVO;
import com.Jason.orderservice.entity.Oorder;
import com.Jason.orderservice.mapper.OrderMapper;
import com.Jason.orderservice.service.OrderService;
import com.Jason.orderservice.utils.OrderNoUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Oorder> implements OrderService {

    @Autowired
    private com.Jason.orderservice.client.ucenterClient ucenterClient;
    @Autowired
    private com.Jason.orderservice.client.videoClient videoClient;
    //创建订单返回订单号
    @Override
    public String createOrder(String videoId, String memberIdByJwtToken) {

        //根据token获取用户id, 远程调用根据用户id获取用户信息
        UCENTERMEMBER member = ucenterClient.getById(memberIdByJwtToken);
        System.out.println(member.getMobile());
        //远程调用根据videoId获取video信息
        videoInfoVO videoInfoVO = videoClient.getVideoInfoForOrder(videoId);

        //创建order对象,赋值
        Oorder oorder = new Oorder();
        oorder.setOrderNo(OrderNoUtil.getOrderNo());
        oorder.setVideoId(videoId);
        oorder.setVideoTitle(videoInfoVO.getTitle());
        oorder.setVideoCover(videoInfoVO.getCover());
        oorder.setProducerName(videoInfoVO.getProducerName());
        oorder.setTotalFee(videoInfoVO.getPrice());
        oorder.setMemberId(member.getId());
        oorder.setMobile(member.getMobile());
        oorder.setNickname(member.getNickname());
        oorder.setStatus(0);
        oorder.setPayType(1);
        baseMapper.insert(oorder);

        //返回订单号
        return oorder.getOrderNo();

    }
}

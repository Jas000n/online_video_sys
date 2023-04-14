package com.Jason.orderservice.client;

import com.Jason.common.utils.vo.videoInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "service-pro")
public interface videoClient {

    //根据id查询影视信息,返回一个common utils里的对象,供order service远程调用
    @GetMapping("service/video/getVideoInfoForOrder/{vid}")
    public videoInfoVO getVideoInfoForOrder(@PathVariable("vid") String vid);
}

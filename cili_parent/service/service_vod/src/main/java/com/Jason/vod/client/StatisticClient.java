package com.Jason.vod.client;

import com.Jason.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "service-statistic")
public interface StatisticClient {

    //更新数据,新增注册用户
    @GetMapping("/statistic/addCount/register")
    public R addCountRegister();

    //更新数据,新增登录用户
    @GetMapping("/statistic/addCount/login")
    public R addCountLogin();

    //更新数据,新增视频观看
    @GetMapping("/statistic/addCount/view")
    public R addCountView();

    //更新数据,新增视频上传
    @GetMapping("/statistic/addCount/video")
    public R addCountVideo();
}
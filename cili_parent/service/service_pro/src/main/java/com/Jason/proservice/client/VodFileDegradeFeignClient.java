package com.Jason.proservice.client;

import com.Jason.common.utils.R;
import org.springframework.stereotype.Component;

//服务熔断后的兜底方法
@Component
public class VodFileDegradeFeignClient implements VodClient{
    @Override
    public R removeAliYunVideo(String id) {
        return R.error().message("删除视频VOD服务出错了!");
    }
}

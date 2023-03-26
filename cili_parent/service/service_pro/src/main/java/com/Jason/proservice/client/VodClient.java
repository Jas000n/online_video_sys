package com.Jason.proservice.client;

import com.Jason.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "service-vod")
public interface VodClient {
    //定义需要调用方法的路径
    //根据id删除episode的视频
    @DeleteMapping("/vod/video/removeAliYunVideo/{id}")
    public R removeAliYunVideo(@PathVariable("id") String id);
}

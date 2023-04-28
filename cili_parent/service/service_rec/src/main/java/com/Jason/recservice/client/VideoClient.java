package com.Jason.recservice.client;

import com.Jason.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(value = "service-pro")
public interface VideoClient {
    //根据影视列表获得影视信息
    @PostMapping("service/videoFront/getRecVideoInfo")
    public R getRecVideoInfo(@RequestBody String[] VIDs);
}

package com.Jason.recservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "service-py-rec")
public interface pyRecClient {

    //测试接口是否跑通
    @GetMapping("/py")
    public String test();
}

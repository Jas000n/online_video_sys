package com.Jason.orderservice.client;

import com.Jason.common.utils.vo.UCENTERMEMBER;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "service-ucenter")
public interface ucenterClient {

    //根据memberID得到member info
    @GetMapping("ucenter/getById/{id}")
    public UCENTERMEMBER getById(@PathVariable("id") String id);
}

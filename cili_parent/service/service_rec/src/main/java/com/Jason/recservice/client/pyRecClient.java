package com.Jason.recservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "service-py-rec")
public interface pyRecClient {

    //获得推荐影视id列表
    @GetMapping("/py_rec/{id}/{k}")
    public String getKRec(@PathVariable String id, @PathVariable String k);
}

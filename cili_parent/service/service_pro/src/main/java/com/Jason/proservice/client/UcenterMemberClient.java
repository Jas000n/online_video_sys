package com.Jason.proservice.client;



import com.Jason.common.utils.vo.UCENTERMEMBER;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name="service-ucenter",fallback = UcenterMemberDegradeFeignClient.class)
public interface UcenterMemberClient {

    //根据用户id获取用户信息
    @GetMapping("/ucenter/getById/{memberId}")
    public UCENTERMEMBER getById(@PathVariable("memberId") String memberId);
}
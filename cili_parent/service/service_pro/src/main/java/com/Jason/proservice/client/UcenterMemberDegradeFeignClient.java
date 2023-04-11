package com.Jason.proservice.client;


import com.Jason.common.utils.vo.UCENTERMEMBER;
import org.springframework.stereotype.Component;

@Component
public class UcenterMemberDegradeFeignClient implements UcenterMemberClient{
    @Override
    public UCENTERMEMBER getById(String memberId) {
        return null;
    }
}

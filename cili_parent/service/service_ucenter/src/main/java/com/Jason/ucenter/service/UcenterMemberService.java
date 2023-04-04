package com.Jason.ucenter.service;

import com.Jason.ucenter.entity.UcenterMember;
import com.Jason.ucenter.entity.vo.LoginVO;
import com.Jason.ucenter.entity.vo.RegisterVO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UcenterMemberService extends IService<UcenterMember> {
    //注册
    void register(RegisterVO registerVo);
    //登录
    String login(LoginVO loginVo);
}

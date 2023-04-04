package com.Jason.ucenter.controller;

import com.Jason.common.utils.JwtUtils;
import com.Jason.common.utils.R;
import com.Jason.ucenter.entity.UcenterMember;
import com.Jason.ucenter.entity.vo.LoginVO;
import com.Jason.ucenter.entity.vo.RegisterVO;
import com.Jason.ucenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/ucenter")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;
    //登录
    @PostMapping("login")
    public R login(@RequestBody LoginVO loginVo) {
        String token = memberService.login(loginVo);
        return R.ok().data("token", token);
    }
    //注册
    @PostMapping("register")
    public R register(@RequestBody RegisterVO registerVo){
        memberService.register(registerVo);
        return R.ok();
    }

    //根据token获得用户信息
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request){
        //调用工具类的方法
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库,根据id获取信息
        UcenterMember member = memberService.getById(memberId);

        return R.ok().data("userInfo",member);
    }
}

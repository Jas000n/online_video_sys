package com.Jason.proservice.controller;

import com.Jason.common.utils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/user")
@CrossOrigin
public class LoginController {

    //login
    @PostMapping("login")
    public R login(){
        System.out.println("有人登录");
        return R.ok().data("token","admin");//暂时把token写死成admin
    }

    @GetMapping("info")
    public R info(){
        System.out.println("调用info");
        return R.ok().
                data("roles","[admin]").data("name","admin").
                data("avatar","https://s2.best-wallpaper.net/wallpaper/1920x1200/1809/Cute-furry-kitten-basket_1920x1200.jpg");
    }
}

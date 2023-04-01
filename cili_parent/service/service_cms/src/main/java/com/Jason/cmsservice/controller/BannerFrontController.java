package com.Jason.cmsservice.controller;

import com.Jason.cmsservice.entity.CrmBanner;
import com.Jason.cmsservice.service.CrmBannerService;
import com.Jason.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cms/frontBannner")
@CrossOrigin
//供前台用户调用,查看banner的接口
public class BannerFrontController {
    @Autowired
    private CrmBannerService crmBannerService;

    //查询所有banner,进行幻灯片显示
    @GetMapping("getAll")
    public R getAllBanner(){
        List<CrmBanner> allBanners = crmBannerService.getAllBanner();
        return R.ok().data("list",allBanners);

    }
}

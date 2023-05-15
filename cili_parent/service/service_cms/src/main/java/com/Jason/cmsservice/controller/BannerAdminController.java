package com.Jason.cmsservice.controller;

import com.Jason.cmsservice.entity.CrmBanner;
import com.Jason.cmsservice.service.CrmBannerService;
import com.Jason.common.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cms/adminBannner")
//@CrossOrigin
//供后台管理员调用进行管理的接口
public class BannerAdminController {
    @Autowired
    private CrmBannerService crmBannerService;

    //分页查询banner
    @GetMapping("pageBanner/{current}/{limit}")
    //current 当前页， limit每页记录数
    public R pageListBanner(@PathVariable long current, @PathVariable long limit){
        Page<CrmBanner> pageBanner = new Page<>(current,limit);
        //调用完方法后，所有分页数据都被封装到pageBanner对象里面
        crmBannerService.page(pageBanner,null);

        long total = pageBanner.getTotal();//总记录数
        List<CrmBanner> records = pageBanner.getRecords();//数据list集合

        return R.ok().data("total",total).data("rows",records);
    }

    //添加banner的方法
    @PostMapping("addBanner")
    public R addProducer(@RequestBody CrmBanner banner){
        boolean save = crmBannerService.save(banner);
        if(save){
            return R.ok();
        }else{
            return R.error();
        }
    }

    //根据banner id进行查询
    @GetMapping("getBanner/{id}")
    public R getProducer(@PathVariable String id){
        CrmBanner banner = crmBannerService.getById(id);
        return R.ok().data("Banner",banner);

    }

    //Banner修改功能
    @PostMapping("updateBanner")
    public R updateProducer(@RequestBody CrmBanner Banner){
        boolean b = crmBannerService.updateById(Banner);
        if(b){
            return R.ok();
        }else{
            return R.error();
        }

    }
    //获取全部bannner
    @GetMapping("getAll")
    public R getAll(){
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        List<CrmBanner> list = crmBannerService.list(queryWrapper);
        return R.ok().data("banners",list);
    }
    //根据id删除banner
    @DeleteMapping("removeById/{id}")
    public R removeById(@PathVariable String id){
        crmBannerService.removeById(id);
        return R.ok();
    }
}


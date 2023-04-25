package com.Jason.recservice.controller;

import com.Jason.common.utils.JwtUtils;
import com.Jason.common.utils.R;
import com.Jason.recservice.entity.Collection;
import com.Jason.recservice.service.collectionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rec")
public class collectionController {
    @Autowired
    private collectionService collectionService;

    //传入vid根据token解析uid,进行影视收藏
    @GetMapping("like/{v_id}")
    public R likeVideo(@PathVariable String v_id, HttpServletRequest httpServletRequest){
        String u_id = JwtUtils.getMemberIdByJwtToken(httpServletRequest);
        Collection newCollection = new Collection();
        newCollection.setUId(u_id);
        newCollection.setVId(v_id);
        collectionService.save(newCollection);
        return R.ok();
    }
    //传入vid根据token解析uid,进行取消影视收藏
    @GetMapping("dislike/{v_id}")
    public R dislikeVideo(@PathVariable String v_id, HttpServletRequest httpServletRequest){
        String u_id = JwtUtils.getMemberIdByJwtToken(httpServletRequest);
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("v_id",v_id).eq("u_id",u_id);
        boolean remove = collectionService.remove(queryWrapper);
        return R.ok();
    }
    //传入vid 和 uid 获取喜欢结果
    @GetMapping("getLikeState/{v_id}")
    public R getLikeState(@PathVariable String v_id, HttpServletRequest httpServletRequest){
        String u_id = JwtUtils.getMemberIdByJwtToken(httpServletRequest);
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("u_id",u_id).eq("v_id",v_id);
        int count = collectionService.count(queryWrapper);
        Boolean result = false;
        if(count>=1){
            result = true;
        }
        return R.ok().data("state",result);
    }
    @GetMapping("test")
    public void sqlToCsv(){
        //这里导出会有mysql安全问题
        //collectionService.sqlToCsv();
        collectionService.mysqlDataToCsv();
    }
}

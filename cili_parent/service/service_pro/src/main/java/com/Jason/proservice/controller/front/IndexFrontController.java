package com.Jason.proservice.controller.front;

import com.Jason.common.utils.R;
import com.Jason.proservice.entity.Producer;
import com.Jason.proservice.entity.Video;
import com.Jason.proservice.service.ProducerService;
import com.Jason.proservice.service.VideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/service/indexFront")
public class IndexFrontController {

    @Autowired
    private ProducerService producerService;
    @Autowired
    private VideoService videoService;
    //查询前8条热门影视,查询前四个创作者
    @GetMapping("index")
    public R index(){
        QueryWrapper<Producer> producerWrapper = new QueryWrapper<>();
        producerWrapper.orderByDesc("rand()");
        producerWrapper.last("limit 4");
        List<Producer> producerlist = producerService.list(producerWrapper);

        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.orderByDesc("view_count");
        videoQueryWrapper.last("limit 8");
        List<Video> videoList = videoService.list(videoQueryWrapper);

        return R.ok().data("videos",videoList).data("producers",producerlist);
    }


}

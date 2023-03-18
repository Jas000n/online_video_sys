package com.Jason.proservice.controller;

import com.Jason.common.utils.R;
import com.Jason.proservice.entity.vo.VideoInfoVO;
import com.Jason.proservice.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/video")
@CrossOrigin
public class VideoController {

    @Autowired
    private VideoService videoService;

    //添加影视基本信息的方法
    @PostMapping("/addVideoInfo")
    public R addVideoInfo(@RequestBody VideoInfoVO videoInfoVO){
        videoService.saveVideoInfo(videoInfoVO);
        return R.ok();
    }
}

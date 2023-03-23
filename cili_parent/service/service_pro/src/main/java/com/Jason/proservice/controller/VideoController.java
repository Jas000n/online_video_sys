package com.Jason.proservice.controller;

import com.Jason.common.utils.R;
import com.Jason.proservice.entity.Video;
import com.Jason.proservice.entity.vo.VideoInfoVO;
import com.Jason.proservice.entity.vo.VideoPublishVO;
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
        //返回添加之后的影视id
        String id = videoService.saveVideoInfo(videoInfoVO);
        return R.ok().data("videoId",id);
    }

    //根据影视id查询影视信息
    @GetMapping("/getVideoInfo/{videoId}")
    public R getVideoInfo(@PathVariable String videoId){
        VideoInfoVO videoInfoVO = videoService.getVideoInfo(videoId);
        return R.ok().data("videoInfo",videoInfoVO);
    }

    //修改影视信息
    @PostMapping("updateVideoInfo")
    public R updateVideoInfo(@RequestBody VideoInfoVO videoInfoVO){
        videoService.updateVideoInfo(videoInfoVO);
        return R.ok();
    }

    //根据videoId查询影视确认信息
    @GetMapping("getPublishVideoInfo/{id}")
    public R getPublishVideoInfo(@PathVariable String id){
        VideoPublishVO videoPublishVO = videoService.getPublishVideoInfo(id);
        return R.ok().data("publishVideoInfo",videoPublishVO);
    }
    //最终发布课程
    @PostMapping("publishVideo/{videoId}")
    public R publishVideo(@PathVariable String videoId){
        Video video = new Video();
        video.setId(videoId);
        video.setStatus("Normal");
        videoService.updateById(video);
        return R.ok();
    }
}

package com.Jason.vod.controller;

import com.Jason.common.utils.R;
import com.Jason.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/vod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;
    //上传视频到阿里云
    @PostMapping("uploadAliYunVideo")
    public R uploadAliYunVideo(MultipartFile file) throws IOException {
        String videoId = vodService.uploadAliYunVideo(file);
        return R.ok().data("videoId",videoId);
    }

}

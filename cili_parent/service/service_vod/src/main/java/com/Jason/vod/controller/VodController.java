package com.Jason.vod.controller;

import com.Jason.common.utils.R;
import com.Jason.vod.service.VodService;
import com.Jason.vod.utils.ConstVodUtils;
import com.Jason.vod.utils.initVodObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    //根据id删除episode的id
    @DeleteMapping("removeAliYunVideo/{id}")
    public R removeAliYunVideo(@PathVariable String id) throws ClientException {
        //初始化对象
        DefaultAcsClient client = initVodObject.initVodClient(ConstVodUtils.ACCESS_KEY_ID, ConstVodUtils.ACCESS_KEY_SECRET);
        //初始化删除视频request
        DeleteVideoRequest request = new DeleteVideoRequest();
        //向request里set
        request.setVideoIds(id);
        //发request
        DeleteVideoResponse acsResponse = client.getAcsResponse(request);
        System.out.println(acsResponse);
        return R.ok();
    }

}

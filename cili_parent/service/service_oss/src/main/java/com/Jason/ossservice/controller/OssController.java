package com.Jason.ossservice.controller;

import com.Jason.common.utils.R;
import com.Jason.ossservice.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/oss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;
    //上传头像的方法
    @PostMapping
    public R uploadOssFile(MultipartFile file) throws IOException {
        //文件路径
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }
}

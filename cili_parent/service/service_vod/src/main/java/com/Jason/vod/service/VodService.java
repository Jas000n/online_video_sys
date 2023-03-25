package com.Jason.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VodService {
    //上传视频到阿里云
    String uploadAliYunVideo(MultipartFile file) throws IOException;
}

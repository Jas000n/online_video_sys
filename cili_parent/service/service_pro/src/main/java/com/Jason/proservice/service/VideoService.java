package com.Jason.proservice.service;

import com.Jason.proservice.entity.Video;
import com.Jason.proservice.entity.vo.VideoInfoVO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface VideoService extends IService<Video> {
    void saveVideoInfo(VideoInfoVO videoInfoVO);
}

package com.Jason.proservice.service;

import com.Jason.proservice.entity.Video;
import com.Jason.proservice.entity.vo.VideoInfoVO;
import com.Jason.proservice.entity.vo.VideoPublishVO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface VideoService extends IService<Video> {
    String saveVideoInfo(VideoInfoVO videoInfoVO);

    VideoInfoVO getVideoInfo(String videoId);

    void updateVideoInfo(VideoInfoVO videoInfoVO);

    VideoPublishVO getPublishVideoInfo(String id);

    //在删除影视的时候,顺便把影视下的所有集和季都删了
    void removeVideoById(String id);
}

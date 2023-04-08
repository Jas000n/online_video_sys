package com.Jason.proservice.service;

import com.Jason.proservice.entity.Video;
import com.Jason.proservice.entity.vo.VideoInfoVO;
import com.Jason.proservice.entity.vo.VideoPublishVO;
import com.Jason.proservice.entity.vo.frontVO.videoFrontVO;
import com.Jason.proservice.entity.vo.frontVO.videoWebVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface VideoService extends IService<Video> {
    String saveVideoInfo(VideoInfoVO videoInfoVO);

    VideoInfoVO getVideoInfo(String videoId);

    void updateVideoInfo(VideoInfoVO videoInfoVO);

    VideoPublishVO getPublishVideoInfo(String id);

    //在删除影视的时候,顺便把影视下的所有集和季都删了
    void removeVideoById(String id);
    //前台条件查询带分页查询影视
    Map<String,Object> getVideoFrontList(Page<Video> videoPage, videoFrontVO videoFrontVO);

    //根据id查询详细信息
    videoWebVO getBaseInfo(String videoId);
}

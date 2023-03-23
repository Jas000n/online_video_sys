package com.Jason.proservice.service;


import com.Jason.proservice.entity.VideoDescription;
import com.baomidou.mybatisplus.extension.service.IService;

public interface VideoDescriptionService extends IService<VideoDescription> {
    //根据影视id删除description
    void removeByVideoId(String id);
}

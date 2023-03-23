package com.Jason.proservice.service;

import com.Jason.proservice.entity.Episode;
import com.baomidou.mybatisplus.extension.service.IService;

public interface EpisodeService extends IService<Episode> {
    //根据影视id删除
    void removeByVideoId(String id);
}

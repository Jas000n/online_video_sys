package com.Jason.proservice.service.impl;

import com.Jason.proservice.entity.Episode;
import com.Jason.proservice.mapper.EpisodeMapper;
import com.Jason.proservice.service.EpisodeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EpisodeServiceImpl extends ServiceImpl<EpisodeMapper, Episode> implements EpisodeService {
    //根据影视id删除
    //TODO 删除episode的时候,还需要删除对应的视频文件
    @Override
    public void removeByVideoId(String id) {
        QueryWrapper<Episode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("video_id",id);
        baseMapper.delete(queryWrapper);
    }
}

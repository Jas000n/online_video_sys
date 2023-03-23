package com.Jason.proservice.service.impl;

import com.Jason.proservice.entity.VideoDescription;
import com.Jason.proservice.mapper.VideoDescriptionMapper;
import com.Jason.proservice.service.VideoDescriptionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class VideoDescriptionServiceImpl extends ServiceImpl<VideoDescriptionMapper, VideoDescription> implements VideoDescriptionService {
    @Override
    public void removeByVideoId(String id) {
        QueryWrapper<VideoDescription> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        baseMapper.delete(queryWrapper);
    }
}

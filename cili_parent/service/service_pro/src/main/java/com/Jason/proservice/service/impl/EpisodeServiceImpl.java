package com.Jason.proservice.service.impl;

import com.Jason.proservice.entity.Episode;
import com.Jason.proservice.mapper.EpisodeMapper;
import com.Jason.proservice.service.EpisodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EpisodeServiceImpl extends ServiceImpl<EpisodeMapper, Episode> implements EpisodeService {
}

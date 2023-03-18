package com.Jason.proservice.service.impl;

import com.Jason.proservice.entity.Season;
import com.Jason.proservice.mapper.SeasonMapper;
import com.Jason.proservice.service.SeasonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SeasonServiceImpl extends ServiceImpl<SeasonMapper, Season> implements SeasonService {
}

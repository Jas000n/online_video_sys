package com.Jason.proservice.service;

import com.Jason.proservice.entity.Season;
import com.Jason.proservice.entity.vo.SeasonVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SeasonService extends IService<Season> {
    //根据影视id,查询所有季
    List<SeasonVO> getSeasonById(String videoId);
}

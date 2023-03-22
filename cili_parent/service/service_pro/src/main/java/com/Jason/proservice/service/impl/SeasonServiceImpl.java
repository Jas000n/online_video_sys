package com.Jason.proservice.service.impl;

import com.Jason.proservice.entity.Episode;
import com.Jason.proservice.entity.Season;
import com.Jason.proservice.entity.vo.EpisodeVO;
import com.Jason.proservice.entity.vo.SeasonVO;
import com.Jason.proservice.mapper.SeasonMapper;
import com.Jason.proservice.service.EpisodeService;
import com.Jason.proservice.service.SeasonService;
import com.Jason.servicebase.exceptionhandler.CiliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeasonServiceImpl extends ServiceImpl<SeasonMapper, Season> implements SeasonService {

    //用于episode查询
    @Autowired
    private EpisodeService episodeService;
    //根据影视id,查询所有季
    @Override
    public List<SeasonVO> getSeasonById(String videoId) {

        //根据影视id查询所有季
        QueryWrapper<Season> wrapperSeason = new QueryWrapper<>();
        wrapperSeason.eq("video_id",videoId);
        List<Season> seasonList = baseMapper.selectList(wrapperSeason);

        //根据影视id查询所有集
        QueryWrapper<Episode> wrapperEpisode = new QueryWrapper<>();
        wrapperSeason.eq("video_id",videoId);
        List<Episode> EpisodeList = episodeService.list(wrapperEpisode);

        //遍历季,对把集塞进去
        List<SeasonVO> result = new ArrayList<>();
        for(Season season:seasonList){
            SeasonVO seasonVO = new SeasonVO();
            BeanUtils.copyProperties(season,seasonVO);
            result.add(seasonVO);

            //遍历季和集,把集塞进对应的季
            List<EpisodeVO> episodeVOList = new ArrayList<>();
            for(Episode episode:EpisodeList){
                if(episode.getSeasonId().equals(season.getId())){
                    EpisodeVO episodeVO = new EpisodeVO();
                    BeanUtils.copyProperties(episode,episodeVO);
                    episodeVOList.add(episodeVO);
                }
            }
            seasonVO.setChildren(episodeVOList);
        }


        return result;
    }
    //尽在该season里没有episode的时候允许删除
    @Override
    public Boolean deleteSeason(String seasonId) {
        //根据seasonId去episode这张表里查询,如果能查到某episode属于这一季,就不删除
        QueryWrapper<Episode> wrapper = new QueryWrapper<>();
        wrapper.eq("season_id",seasonId);
        int count = episodeService.count(wrapper);
        if(count>0){
            //有数据,不能删除
            throw new CiliException(20001,"该季不为空,不能删除");
        }else{
            //没数据,随便删除
            int result = baseMapper.deleteById(seasonId);
            return (result>0);
        }

    }
}

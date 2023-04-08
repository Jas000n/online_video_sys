package com.Jason.proservice.service.impl;

import com.Jason.proservice.entity.Producer;
import com.Jason.proservice.entity.Video;
import com.Jason.proservice.entity.VideoDescription;
import com.Jason.proservice.entity.vo.VideoInfoVO;
import com.Jason.proservice.entity.vo.VideoPublishVO;
import com.Jason.proservice.entity.vo.frontVO.videoFrontVO;
import com.Jason.proservice.entity.vo.frontVO.videoWebVO;
import com.Jason.proservice.mapper.VideoMapper;
import com.Jason.proservice.service.EpisodeService;
import com.Jason.proservice.service.SeasonService;
import com.Jason.proservice.service.VideoDescriptionService;
import com.Jason.proservice.service.VideoService;
import com.Jason.servicebase.exceptionhandler.CiliException;
import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private VideoDescriptionService videoDescriptionService;
    @Autowired
    private SeasonService seasonService;
    @Autowired
    private EpisodeService episodeService;
    //添加影视信息的基本方法
    @Override
    public String saveVideoInfo(VideoInfoVO videoInfoVO) {
        Video toSave = new Video();
        //直接赋值
        BeanUtils.copyProperties(videoInfoVO,toSave);
        //向影视表里添加影视基本信息
        //rows effected
        int result = baseMapper.insert(toSave);
        if(result <= 0){
            //添加失败
            throw new CiliException(20001,"添加影视信息失败");
        }

        //影视id和影视描述id一致，这样可以使其两两对应
        String VID = toSave.getId();

        //向影视简介里添加影视简介基本信息
        VideoDescription videoDescription = new VideoDescription();
        videoDescription.setDescription(videoInfoVO.getDescription());
        videoDescription.setId(VID);
        videoDescriptionService.save(videoDescription);

        return VID;

    }

    //根据影视id查询信息
    @Override
    public VideoInfoVO getVideoInfo(String videoId) {
        //先查询影视信息
        Video video = baseMapper.selectById(videoId);
        //再查询影视描述信息
        VideoDescription videoDescription = videoDescriptionService.getById(video);
        //赋值
        VideoInfoVO videoInfoVO = new VideoInfoVO();
        BeanUtils.copyProperties(video,videoInfoVO);
        videoInfoVO.setDescription(videoDescription.getDescription());

        return videoInfoVO;
    }

    //修改影视信息
    @Override
    public void updateVideoInfo(VideoInfoVO videoInfoVO) {

        //修改video表
        Video video = new Video();
        BeanUtils.copyProperties(videoInfoVO,video);
        int updateRows = baseMapper.updateById(video);
        if(updateRows ==0){
            throw new CiliException(20001,"修改影视信息失败!");
        }

        //修改videoDescription表
        VideoDescription videoDescription = new VideoDescription();
        videoDescription.setId(videoInfoVO.getId());
        videoDescription.setDescription(videoDescription.getDescription());
        Boolean result = videoDescriptionService.updateById(videoDescription);
        if(!result){
            throw new CiliException(20001,"修改影视描述信息失败!");
        }

    }
    //根据videoId查询影视确认信息
    @Override
    public VideoPublishVO getPublishVideoInfo(String id) {
        //调用自己写的mapper
        VideoPublishVO publishVideoInfo = baseMapper.getPublishVideoInfo(id);
        return publishVideoInfo;
    }

    //在删除影视的时候,顺便把影视下的所有集和季都删了
    @Override
    public void removeVideoById(String id) {
        //根据影视id删除episode
        episodeService.removeByVideoId(id);
        //根据影视id删除season
        seasonService.removeByVideoId(id);
        //根据影视id删除description
        videoDescriptionService.removeByVideoId(id);
        //删除video
        int result = baseMapper.deleteById(id);
        if(result==0){
            throw new CiliException(20001,"删除视频失败!");
        }
    }

    //前台条件查询带分页查询影视
    @Override
    public Map<String, Object> getVideoFrontList(Page<Video> videoPage, videoFrontVO videoFrontVO) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(videoFrontVO.getClassificationParentId())){
            queryWrapper.eq("classification_parent_id",videoFrontVO.getClassificationParentId());
        }
        if(!StringUtils.isEmpty(videoFrontVO.getClassificationId())){
            queryWrapper.eq("classification_id",videoFrontVO.getClassificationId());
        }
        if(!StringUtils.isEmpty(videoFrontVO.getBuyCountSort())){
            queryWrapper.orderByDesc("buy_count");
        }
        if(!StringUtils.isEmpty(videoFrontVO.getPriceSort())){
            queryWrapper.orderByDesc("price");
        }
        if(!StringUtils.isEmpty(videoFrontVO.getGmtCreateSort())){
            queryWrapper.orderByDesc("gmt_create");
        }


        baseMapper.selectPage(videoPage,queryWrapper);

        List<Video> records = videoPage.getRecords();
        long current = videoPage.getCurrent();
        long pages = videoPage.getPages();
        long size = videoPage.getSize();
        long total = videoPage.getTotal();
        boolean hasNext = videoPage.hasNext();
        boolean hasPrevious =videoPage.hasPrevious();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

    //根据id查询详细信息
    @Override
    public videoWebVO getBaseInfo(String videoId) {
        return baseMapper.getBaseVideoInfo(videoId);
    }
}

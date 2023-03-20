package com.Jason.proservice.service.impl;

import com.Jason.proservice.entity.Video;
import com.Jason.proservice.entity.VideoDescription;
import com.Jason.proservice.entity.vo.VideoInfoVO;
import com.Jason.proservice.mapper.VideoMapper;
import com.Jason.proservice.service.VideoDescriptionService;
import com.Jason.proservice.service.VideoService;
import com.Jason.servicebase.exceptionhandler.CiliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private VideoDescriptionService videoDescriptionService;
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
}

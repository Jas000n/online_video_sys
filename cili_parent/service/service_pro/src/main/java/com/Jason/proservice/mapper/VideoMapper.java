package com.Jason.proservice.mapper;

import com.Jason.proservice.entity.Video;
import com.Jason.proservice.entity.vo.VideoPublishVO;
import com.Jason.proservice.entity.vo.frontVO.videoWebVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface VideoMapper extends BaseMapper<Video> {

    public VideoPublishVO getPublishVideoInfo(String videoId);

    videoWebVO getBaseVideoInfo(String videoId);
}

package com.Jason.proservice.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VideoInfoVO {

    //影视id
    private String id;

    //创作者id
    private String producerId;

    //影视分类id
    private String classificationId;

    //一级影视分类id
    private String classificationParentId;

    //影视标题
    private String title;

    //价格
    private BigDecimal price;

    //总集数
    private Integer episodeNum;

    //影视封面链接
    private String cover;

    //影视简介
    private String description;
}

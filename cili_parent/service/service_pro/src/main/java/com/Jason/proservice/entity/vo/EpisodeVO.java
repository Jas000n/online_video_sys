package com.Jason.proservice.entity.vo;

import lombok.Data;

@Data
public class EpisodeVO {

    private String id;
    //名称
    private String title;

    private Integer isFree;

    private String videoSourceId;
}

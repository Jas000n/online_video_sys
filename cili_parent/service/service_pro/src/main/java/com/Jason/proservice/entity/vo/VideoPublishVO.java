package com.Jason.proservice.entity.vo;

import lombok.Data;

@Data
public class VideoPublishVO {
    private String id;
    private String title;
    private String cover;
    private Integer episodeNum;
    private String classificationId;
    private String classificationParentId;
    private String producerName;
    private String price;
    private String description;

}

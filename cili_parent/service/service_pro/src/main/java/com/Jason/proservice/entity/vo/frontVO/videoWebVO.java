package com.Jason.proservice.entity.vo.frontVO;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class videoWebVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String title;

    private BigDecimal price;

    private Integer episodeNum;

    private String cover;

    private Long buyCount;

    private Long viewCount;

    private String description;

    private String producerId;

    private String producerName;

    private String intro;

    private String avatar;

    private String classificationParentId;

    private String classificationParent;

    private String classificationId;

    private String classification;
}

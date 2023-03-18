package com.Jason.proservice.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
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

    //购买次数统计
    private Long buyCount;

    //观看次数统计
    private Long viewCount;

    //版本。乐观锁
    private Long version;

    //课程状态， draft表示未发布， normal表示已发布
    private String status;

    //逻辑删除
    @TableLogic
    private Integer isDeleted;

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;






}

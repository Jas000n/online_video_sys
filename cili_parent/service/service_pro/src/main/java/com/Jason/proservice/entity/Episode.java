package com.Jason.proservice.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Episode implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    //影视id
    private String videoId;
    //季id
    private String seasonId;

    private String title;

    private String videoSourceId;

    private String videoOriginalName;

    private String playCount;

    private Integer sort;

    private Integer isFree;

    private String duration;

    private String status;

    private String size;

    private Long version;

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}

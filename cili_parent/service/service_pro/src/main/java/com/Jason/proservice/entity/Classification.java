package com.Jason.proservice.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class Classification implements Serializable {

    private static final long serialVersionUID = 1;

    @TableId(value = "id",type = IdType.ID_WORKER_STR)
    private String id;

    private String title;

    private String parentId;

    private Integer sort;

    //自动填充
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    //自动填充
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}

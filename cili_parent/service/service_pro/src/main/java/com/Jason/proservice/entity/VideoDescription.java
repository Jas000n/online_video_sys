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
public class VideoDescription implements Serializable {

    private static final long serialVersionUID = 1L;

    //input表示需要手动输入而非自动生成，indie要与vide的id一一对应
    @TableId(value = "id", type = IdType.INPUT)
    private String id;


    private String description;

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}

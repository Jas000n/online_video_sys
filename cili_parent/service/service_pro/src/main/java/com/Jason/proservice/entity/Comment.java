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
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    //影视id
    private String episodeId;

//    //创作者id
//    private String producerId;

    //会员id
    private String memberId;

    //会员昵称
    private String nickname;

    //头像
    private String avatar;

    //评论内容
    private String content;

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


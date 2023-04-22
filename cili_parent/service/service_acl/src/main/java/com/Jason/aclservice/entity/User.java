package com.Jason.aclservice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("acl_user")
//"User对象", description="用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    //"会员id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    //"微信openid")
    private String username;

    //"密码")
    private String password;

    //"昵称")
    private String nickName;

    //"用户头像")
    private String salt;

    //"用户签名")
    private String token;

    @TableLogic
    private Integer isDeleted;

    //自动填充
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    //自动填充
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}

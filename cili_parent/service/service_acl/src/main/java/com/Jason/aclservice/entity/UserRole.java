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
@TableName("acl_user_role")
//"UserRole对象", description="")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    //"主键id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    //"角色id")
    private String roleId;

    //"用户id")
    private String userId;

    @TableLogic
    private Integer isDeleted;

    //自动填充
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    //自动填充
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;



}

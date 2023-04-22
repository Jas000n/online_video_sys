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
@TableName("acl_role")
//"Role对象", description="")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

     //"角色id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    //"角色名称")
    private String roleName;

    //"角色编码")
    private String roleCode;

    // "备注")
    private String remark;

    @TableLogic
    private Integer isDeleted;

    //自动填充
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    //自动填充
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}

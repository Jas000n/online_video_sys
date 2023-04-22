package com.Jason.aclservice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("acl_permission")
//Permission对象", description="权限")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    //"编号")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    //"所属上级")
    private String pid;

    //"名称")
    private String name;

    //"类型(1:菜单,2:按钮)")
    private Integer type;

    // "权限值")
    private String permissionValue;

    // "访问路径")
    private String path;

    //"组件路径")
    private String component;

    // "图标")
    private String icon;

    //"状态(0:禁止,1:正常)")
    private Integer status;

    //"层级")
    @TableField(exist = false)
    private Integer level;

    //"下级")
    @TableField(exist = false)
    private List<Permission> children;

    //"是否选中")
    @TableField(exist = false)
    private boolean isSelect;


    @TableLogic
    private Integer isDeleted;

    //自动填充
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    //自动填充
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}

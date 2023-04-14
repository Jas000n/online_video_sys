package com.Jason.orderservice.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class Oorder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    private String orderNo;

    private String videoId;

    private String videoTitle;

    private String videoCover;

    private String producerName;

    private String memberId;

    private String nickname;

    private String mobile;

    private BigDecimal totalFee;

    private Integer payType;

    private Integer status;


    @TableLogic
    private Integer isDeleted;

    //自动填充
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    //自动填充
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

}


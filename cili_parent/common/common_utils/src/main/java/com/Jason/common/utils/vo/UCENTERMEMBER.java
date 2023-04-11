package com.Jason.common.utils.vo;


import lombok.Data;

import java.util.Date;
@Data
public class UCENTERMEMBER {



    private String id;

    private String openid;

    private String mobile;

    private String password;

    private String nickname;

    private Integer sex;

    private Integer age;

    private String avatar;

    //用户签名
    private String sign;

    private Boolean isDisabled;

    private Integer isDeleted;

    private Date gmtCreate;

    private Date gmtModified;
}

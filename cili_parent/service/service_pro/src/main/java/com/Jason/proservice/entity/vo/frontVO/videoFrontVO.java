package com.Jason.proservice.entity.vo.frontVO;

import lombok.Data;

//用于前台影视详情查询条件传值的值对象
@Data
public class videoFrontVO {
    private String title;
    private String producerId;
    private String classificationParentId;
    private String classificationId;
    private String buyCountSort;
    private String gmtCreateSort;
    private String priceSort;

}

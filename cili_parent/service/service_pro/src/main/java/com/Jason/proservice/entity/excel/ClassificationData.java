package com.Jason.proservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ClassificationData {


    //一级分类
    @ExcelProperty(index = 0)
    private String lv1Classification;

    //二级分类
    @ExcelProperty(index = 1)
    private String lv2Classification;

}

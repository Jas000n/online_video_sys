package com.Jason.proservice.entity.classification;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Lv1Classification {
    private String id;
    private String title;
    //子分类
    private List<Lv2Classification> children =  new ArrayList<>();
}

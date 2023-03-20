package com.Jason.proservice.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SeasonVO {

    private String id;

    //名称
    private String title;

    //一季里面有很多集
    private List<EpisodeVO> children = new ArrayList<>();
}

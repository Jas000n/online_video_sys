package com.Jason.proservice.entity.vo;

import lombok.Data;

@Data
public class VideoQuery {
    private String title;

    private String begin;//查询开始时间

    private String end;//查询结束时间
}

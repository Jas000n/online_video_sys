package com.Jason.proservice.controller;

import com.Jason.common.utils.R;
import com.Jason.proservice.entity.vo.SeasonVO;
import com.Jason.proservice.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/season")
@CrossOrigin
public class SeasonController {

    @Autowired
    private SeasonService seasonService;

    @GetMapping("getSeason/{videoId}")
    public R getSeason(@PathVariable String videoId){

        //根据影视id,查询所有季
        List<SeasonVO> list = seasonService.getSeasonById(videoId);

        return R.ok().data("seasons",list);
    }

}

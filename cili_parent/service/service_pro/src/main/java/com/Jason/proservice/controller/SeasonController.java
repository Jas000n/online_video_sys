package com.Jason.proservice.controller;

import com.Jason.common.utils.R;
import com.Jason.proservice.entity.Season;
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
    //添加影视季
    @PostMapping("/addSeason")
    public R addSeason(@RequestBody Season season){
        seasonService.save(season);
        return R.ok();
    }
    //根据id查询章节
     @GetMapping("/getSeasonInfoById/{seasonId}")
     public R getSeasonInfoBYId(@PathVariable String seasonId){
         Season season = seasonService.getById(seasonId);
         return R.ok().data("season",season);
     }

    //修改影视季
    @PostMapping("/updateSeason")
    public R updateSeason(@RequestBody Season season){
        seasonService.updateById(season);
        return R.ok();
    }

    //删除影视季
    @DeleteMapping("/deleteById/{seasonId}")
    public R deleteById(@PathVariable String seasonId){
        Boolean result = seasonService.deleteSeason(seasonId);
        if(result){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

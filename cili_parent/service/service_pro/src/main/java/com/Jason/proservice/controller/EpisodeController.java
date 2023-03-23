package com.Jason.proservice.controller;

import com.Jason.common.utils.R;
import com.Jason.proservice.entity.Episode;
import com.Jason.proservice.service.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//

@RestController
@RequestMapping("/service/episode")
@CrossOrigin
public class EpisodeController {

    @Autowired
    private EpisodeService episodeService;

    //添加集
    @PostMapping("/addEpisode")
    public R addEpisode(@RequestBody Episode episode){
        episodeService.save(episode);
        return R.ok();
    }

    //删除集
    //TODO 后续要完善:删除该集的时候,需要把集包含的视频等信息都删除
    @DeleteMapping("delete/{id}")
    public R deleteEpisode(@PathVariable String id){
        episodeService.removeById(id);
        return  R.ok();
    }
    //根据id找到集
    @GetMapping("/getEpisodeById/{id}")
    public R getEpisodeById(@PathVariable String id){
        Episode byId = episodeService.getById(id);
        return R.ok().data("episode",byId);
    }
    //修改集
    @PostMapping("updateEpisode")
    public R updateEpisode(@RequestBody Episode episode){
        episodeService.updateById(episode);
        return R.ok();
    }

}

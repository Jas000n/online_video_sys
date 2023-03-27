package com.Jason.proservice.controller;

import com.Jason.common.utils.R;
import com.Jason.proservice.client.VodClient;
import com.Jason.proservice.entity.Episode;
import com.Jason.proservice.service.EpisodeService;
import com.Jason.servicebase.exceptionhandler.CiliException;
import com.alibaba.excel.util.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//

@RestController
@RequestMapping("/service/episode")
@CrossOrigin
public class EpisodeController {

    @Autowired
    private EpisodeService episodeService;
    @Autowired
    private VodClient vodClient;

    //添加集
    @PostMapping("/addEpisode")
    public R addEpisode(@RequestBody Episode episode){
        episodeService.save(episode);
        return R.ok();
    }

    //删除集
    //删除该集的时候,需要把集包含的视频等信息都删除
    @DeleteMapping("delete/{id}")
    public R deleteEpisode(@PathVariable String id){
        //先跟据episode id得到对应的存储在阿里云vod里的视频id,再根据这个id把阿里云里的视频删除
        Episode byId = episodeService.getById(id);
        String AliYunVideoId = byId.getVideoSourceId();
        //如果这集没有上传视频就不用删除了
        if(!StringUtils.isEmpty(AliYunVideoId)){
            R r = vodClient.removeAliYunVideo(AliYunVideoId);
            if(r.getCode()==20001){
                System.out.println("熔断了");
                throw new CiliException(20001,"删除视频失败,服务熔断");
            }
        }
        //删除该集
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

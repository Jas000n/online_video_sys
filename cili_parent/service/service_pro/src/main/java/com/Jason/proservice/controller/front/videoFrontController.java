package com.Jason.proservice.controller.front;

import com.Jason.common.utils.R;
import com.Jason.proservice.entity.Video;
import com.Jason.proservice.entity.vo.SeasonVO;
import com.Jason.proservice.entity.vo.frontVO.videoFrontVO;
import com.Jason.proservice.entity.vo.frontVO.videoWebVO;
import com.Jason.proservice.service.SeasonService;
import com.Jason.proservice.service.VideoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("service/videoFront")
public class videoFrontController {
    @Autowired
    private VideoService videoService;
    @Autowired
    private SeasonService seasonService;

    //条件查询带分页查询影视
    @PostMapping("getFrontVideoList/{page}/{limit}")
    public R getFrontVideoList(@PathVariable Long page, @PathVariable Long limit,
                               @RequestBody(required = false) videoFrontVO videoFrontVO){
        Page<Video> videoPage = new Page<>(page,limit);
        Map<String,Object> map = videoService.getVideoFrontList(videoPage,videoFrontVO);
        return R.ok().data(map);
    }

    //根据id查询影视详情,包含相关的影视集\季,等等,比video entity的信息更多
    @GetMapping("getFrontVideoInfo/{videoId}")
    public R getFrontVideoInfo(@PathVariable String videoId){
        //查询video基本信息
        videoWebVO videoWebVO= videoService.getBaseInfo(videoId);
        //查询季和集
        List<SeasonVO> seasons = seasonService.getSeasonById(videoId);
        return R.ok().data("videoWebVO", videoWebVO).data("seasons",seasons);
    }
}

package com.Jason.proservice.controller.front;

import com.Jason.common.utils.JwtUtils;
import com.Jason.common.utils.R;
import com.Jason.proservice.client.OrderClient;
import com.Jason.proservice.entity.Video;
import com.Jason.proservice.entity.vo.SeasonVO;
import com.Jason.proservice.entity.vo.frontVO.videoFrontVO;
import com.Jason.proservice.entity.vo.frontVO.videoWebVO;
import com.Jason.proservice.service.SeasonService;
import com.Jason.proservice.service.VideoService;
import com.alibaba.excel.event.Order;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
//@CrossOrigin
@RequestMapping("service/videoFront")
public class videoFrontController {
    @Autowired
    private VideoService videoService;
    @Autowired
    private SeasonService seasonService;
    @Autowired
    private OrderClient orderClient;

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
    //根据id查询影视详情信息,包括买没买
    @GetMapping("getVideoInfo/{videoId}")
    public R getVideoInfo(@PathVariable String videoId,  HttpServletRequest request) {


        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        System.out.println("member id!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(memberId);
        System.out.println("end of memebre id !!!!!!!!!!!!");
        if(Objects.equals(memberId, "")){
            System.out.println("没有memberid");
        }
        //查询video基本信息
        videoWebVO videoWebVO= videoService.getBaseInfo(videoId);
        //查询季和集
        List<SeasonVO> seasons = seasonService.getSeasonById(videoId);

        System.out.println(videoId);
        //远程调用，判断影视是否被购买
        boolean isBuy = orderClient.isBuyVideo(memberId, videoId);

        return R.ok().data("videoWebVO",videoWebVO).data("seasons",seasons).data("isbuy",isBuy);

    }
    //根据推荐的影视id获取影视信息
    @PostMapping("getRecVideoInfo")
    public R getRecVideoInfo(@RequestBody String [] VIDs){
        List<Video> result = new ArrayList<>();
        for(String vid:VIDs){
            System.out.println(Arrays.toString(VIDs));
            Video byId = videoService.getById(vid);
            result.add(byId);
        }
        return R.ok().data("item",result);
    }

    //根据传入的字段模糊搜索影视
    @GetMapping("search/{name}")
    public R search(@PathVariable String name){
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title",name).last(" limit 8");
        List<Video> list = videoService.list(queryWrapper);
        int total = list.size();
        return R.ok().data("list",list).data("total",total);
    }
}

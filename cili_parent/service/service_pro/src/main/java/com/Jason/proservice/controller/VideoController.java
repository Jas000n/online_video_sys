package com.Jason.proservice.controller;

import com.Jason.common.utils.R;
import com.Jason.common.utils.vo.videoInfoVO;
import com.Jason.proservice.entity.Video;
import com.Jason.proservice.entity.vo.VideoInfoVO;
import com.Jason.proservice.entity.vo.VideoPublishVO;
import com.Jason.proservice.entity.vo.VideoQuery;
import com.Jason.proservice.entity.vo.frontVO.videoWebVO;
import com.Jason.proservice.service.VideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/video")
@CrossOrigin
public class VideoController {

    @Autowired
    private VideoService videoService;

    //添加影视基本信息的方法
    @PostMapping("/addVideoInfo")
    public R addVideoInfo(@RequestBody VideoInfoVO videoInfoVO){
        //返回添加之后的影视id
        String id = videoService.saveVideoInfo(videoInfoVO);
        return R.ok().data("videoId",id);
    }

    //根据影视id查询影视信息
    @GetMapping("/getVideoInfo/{videoId}")
    public R getVideoInfo(@PathVariable String videoId){
        VideoInfoVO videoInfoVO = videoService.getVideoInfo(videoId);
        return R.ok().data("videoInfo",videoInfoVO);
    }

    //修改影视信息
    @PostMapping("updateVideoInfo")
    public R updateVideoInfo(@RequestBody VideoInfoVO videoInfoVO){
        videoService.updateVideoInfo(videoInfoVO);
        return R.ok();
    }

    //根据videoId查询影视确认信息
    @GetMapping("getPublishVideoInfo/{id}")
    public R getPublishVideoInfo(@PathVariable String id){
        VideoPublishVO videoPublishVO = videoService.getPublishVideoInfo(id);
        return R.ok().data("publishVideoInfo",videoPublishVO);
    }
    //最终发布课程
    @PostMapping("publishVideo/{videoId}")
    public R publishVideo(@PathVariable String videoId){
        Video video = new Video();
        video.setId(videoId);
        video.setStatus("Normal");
        videoService.updateById(video);
        return R.ok();
    }
    //下面的所有方法用于list页面显示****************************************
    //分页查询
    @GetMapping("pageVideo/{current}/{limit}")
    //current 当前页， limit每页记录数
    public R pageListVideo(@PathVariable long current,@PathVariable long limit){
        Page<Video> pageVideo = new Page<>(current,limit);
        //调用完方法后，所有分页数据都被封装到pageVideo对象里面
        videoService.page(pageVideo,null);

        long total = pageVideo.getTotal();//总记录数
        List<Video> records = pageVideo.getRecords();//数据list集合

        return R.ok().data("total",total).data("rows",records);
    }

    //条件查询带分页的方法
    @PostMapping("pageVideoCondition/{current}/{limit}")
    public R pageVideoCondition(@PathVariable long current, @PathVariable long limit,
                                   @RequestBody(required = false) VideoQuery videoQuery){
        //创建page对象
        Page<Video> pageVideo = new Page<>(current,limit);
        //构建条件
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        //多条件组合查询
        //动态sql：判断条件是否唯恐，不为空则拼接条件
        String title = videoQuery.getTitle();
        String begin = videoQuery.getBegin();
        String end = videoQuery.getEnd();

        if(!StringUtils.isEmpty(title)){
            wrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }
        //排序
        wrapper.orderByDesc("gmt_create");
        //调用方法实现条件查询分页
        videoService.page(pageVideo,wrapper);

        //取值，返回结果
        long total = pageVideo.getTotal();//总记录数
        List<Video> records = pageVideo.getRecords();//数据list集合

        return R.ok().data("total",total).data("rows",records);
    }
    //根据id，删除影视,需要把影视下的所有季和集都做删除
    @DeleteMapping("/delete/{id}")
    public R deleteByID(@PathVariable String id){
        videoService.removeVideoById(id);
        return R.ok();
    }

    //根据id查询影视信息,返回一个common utils里的对象,供order service远程调用
    @GetMapping("getVideoInfoForOrder/{vid}")
    public videoInfoVO getVideoInfoForOrder(@PathVariable String vid){
        System.out.println("调用了1111");
        videoWebVO videoWebVO = videoService.getBaseInfo(vid);
        videoInfoVO videoInfoVO = new videoInfoVO();
        BeanUtils.copyProperties(videoWebVO,videoInfoVO);
        return videoInfoVO;
    }

}

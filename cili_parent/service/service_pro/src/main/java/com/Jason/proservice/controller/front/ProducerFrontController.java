package com.Jason.proservice.controller.front;

import com.Jason.common.utils.R;
import com.Jason.proservice.entity.Producer;
import com.Jason.proservice.entity.Video;
import com.Jason.proservice.service.ProducerService;
import com.Jason.proservice.service.VideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin
@RequestMapping("service/producerFront")
public class ProducerFrontController {
    @Autowired
    private ProducerService producerService;
    @Autowired
    private VideoService videoService;

    //分页查询创作者
    @GetMapping("getProducerFrontList/{page}/{limit}")
    public R getProducerFrontList(@PathVariable Long page, @PathVariable Long limit){
        Page<Producer> producerPage = new Page<>(page,limit);
        Map<String,Object> map = producerService.getProducerFrontList(producerPage);

        return R.ok().data(map);
    }

    //根据创作者id查询创作者详情和他所创作者的影视
    @GetMapping("getProducerAndHisVideos/{id}")
    public R getProducerAndHisVideos(@PathVariable String id){
        //查询创作者信息
        Producer producer = producerService.getById(id);
        //查询影视信息
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("producer_id",id);
        List<Video> list = videoService.list(queryWrapper);

        return R.ok().data("producer",producer).data("videoList",list);
    }
}

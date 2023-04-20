package com.Jason.statistic.controller;

import com.Jason.common.utils.R;
import com.Jason.statistic.entity.StatisticsDaily;
import com.Jason.statistic.service.StatisticService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

@RestController
@RequestMapping("statistic")
@CrossOrigin
public class StatisticController {

    @Autowired
    private StatisticService Staservice;


    //生成第二天的统计数据,需要每日定时执行
    @GetMapping("generateSta")
    @Scheduled(cron = "0 0 23 * * * ")
    public void generateSta(){
        // 获取当前时间
        Date nextDay = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(nextDay);
        // 把日期往后增加一天,整数  往后推,负数往前移动
        calendar.add(Calendar.DATE, 1);
        // 这个时间就是日期往后推一天的结果
        nextDay = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nextDate = dateFormat.format(nextDay);

        //写入下一天的数据,此时数据为空
        StatisticsDaily nextDaySta = new StatisticsDaily();
        nextDaySta.setDateCalculated(nextDate.toString());
        Staservice.save(nextDaySta);

    }

    //增加数量,参数有register_num, login_num, video_view_num, video_add
    @GetMapping("/addCount/register")
    public R addCountRegister(){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String toAdd = dateFormat.format(date);

        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",toAdd);
        StatisticsDaily one = Staservice.getOne(wrapper);
        one.setRegisterNum(one.getRegisterNum()+1);
        Staservice.updateById(one);
        return R.ok();
    }
    @GetMapping("/addCount/login")
    public R addCountLogin(){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String toAdd = dateFormat.format(date);

        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",toAdd);
        StatisticsDaily one = Staservice.getOne(wrapper);
        one.setLoginNum(one.getLoginNum()+1);
        Staservice.updateById(one);
        return R.ok();
    }
    @GetMapping("/addCount/view")
    public R addCountView(){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String toAdd = dateFormat.format(date);

        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",toAdd);
        StatisticsDaily one = Staservice.getOne(wrapper);
        one.setVideoViewNum(one.getVideoViewNum()+1);
        Staservice.updateById(one);
        return R.ok();
    }
    @GetMapping("/addCount/video")
    public R addCountVideo(){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String toAdd = dateFormat.format(date);

        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",toAdd);
        StatisticsDaily one = Staservice.getOne(wrapper);
        one.setVideoNum(one.getVideoNum()+1);
        Staservice.updateById(one);
        return R.ok();
    }
    //用于获取统计数据的折线图,包含起始日期,结束日期,查询的类型
    @GetMapping("show-chart/{begin}/{end}/{type}")
    public R showChart(@PathVariable String begin,@PathVariable String end,@PathVariable String type){
        Map<String, Object> map = Staservice.getChartData(begin, end, type);
        return R.ok().data(map);
    }
}

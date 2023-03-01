package com.Jason.proservice.controller;


import com.Jason.common.utils.R;
import com.Jason.proservice.entity.Producer;
import com.Jason.proservice.service.ProducerService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.PrimitiveIterator;

/**
 * <p>
 * 创作者 前端控制器
 * </p>
 *
 * @author Jason
 * @since 2023-02-28
 */
@RestController
@RequestMapping("/proservice/producer")
public class ProducerController {


    @Autowired
    private ProducerService producerService;

    //查询所有创作者数据
    @GetMapping("findAll")
    public R findAllProducer(){
        List<Producer> AllProducerlist = producerService.list(null);
        return R.ok().data("items",AllProducerlist);
    }
    //根据id，逻辑删除讲师
    @DeleteMapping("/delete/{id}")
    public R deleteByID(@PathVariable String id){
        System.out.println(id);
        boolean b = producerService.removeById(id);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("pageProducer/{current}/{limit}")
    //current 当前页， limit每页记录数
    public R pageListProducer(@PathVariable long current,@PathVariable long limit){
        Page<Producer> pageProducer = new Page<>(current,limit);
        //调用完方法后，所有分页数据都被封装到pageProducer对象里面
        producerService.page(pageProducer,null);

        long total = pageProducer.getTotal();//总记录数
        List<Producer> records = pageProducer.getRecords();//数据list集合

        return R.ok().data("total",total).data("rows",records);
    }


}


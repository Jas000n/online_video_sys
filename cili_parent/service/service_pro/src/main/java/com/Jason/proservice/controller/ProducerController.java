package com.Jason.proservice.controller;


import com.Jason.common.utils.R;
import com.Jason.proservice.entity.Producer;
import com.Jason.proservice.entity.vo.ProducerQuery;
import com.Jason.proservice.service.ProducerService;
import com.Jason.servicebase.exceptionhandler.CiliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 创作者 前端控制器
 * </p>
 *
 * @author Jason
 * @since 2023-02-28
 */
@RestController
@CrossOrigin
@RequestMapping("/proservice/producer")
public class ProducerController {


    @Autowired
    private ProducerService producerService;

    //查询所有创作者数据
    @GetMapping("findAll")
    public R findAllProducer(){
        List<Producer> AllProducerlist = producerService.list(null);
        //该段注释用于测试自定义异常处理
//        try{
//            int i = 1/0;
//
//        }catch (Exception e){
//            throw new CiliException(20001,"遇见了该死的除0");
//        }
        return R.ok().data("items",AllProducerlist);
    }
    //根据id，逻辑删除讲师
    @DeleteMapping("/delete/{id}")
    public R deleteByID(@PathVariable String id){
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

    //条件查询带分页的方法
    @PostMapping("pageProducerCondition/{current}/{limit}")
    public R pageProducerCondition(@PathVariable long current, @PathVariable long limit,
                                   @RequestBody(required = false) ProducerQuery producerQuery){
        //创建page对象
        Page<Producer> pageProducer = new Page<>(current,limit);
        //构建条件
        QueryWrapper<Producer> wrapper = new QueryWrapper<>();
        //多条件组合查询
        //动态sql：判断条件是否唯恐，不为空则拼接条件
        String name = producerQuery.getName();
        String begin = producerQuery.getBegin();
        String end = producerQuery.getEnd();

        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
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
        producerService.page(pageProducer,wrapper);

        //取值，返回结果
        long total = pageProducer.getTotal();//总记录数
        List<Producer> records = pageProducer.getRecords();//数据list集合

        return R.ok().data("total",total).data("rows",records);
    }


    //添加创作者的方法
    @PostMapping("addProducer")
    public R addProducer(@RequestBody Producer producer){
        boolean save = producerService.save(producer);
        if(save){
            return R.ok();
        }else{
            return R.error();
        }


    }
    //根据创作者id进行查询
    @GetMapping("getProducer/{id}")
    public R getProducer(@PathVariable String id){
        Producer producer = producerService.getById(id);
        return R.ok().data("Producer",producer);

    }

    //创作者修改功能
    @PostMapping("updateProducer")
    public R updateProducer(@RequestBody Producer producer){
        boolean b = producerService.updateById(producer);
        if(b){
            return R.ok();
        }else{
            return R.error();
        }

    }

}


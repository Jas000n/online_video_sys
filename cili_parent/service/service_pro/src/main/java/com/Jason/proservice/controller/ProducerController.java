package com.Jason.proservice.controller;


import com.Jason.proservice.entity.Producer;
import com.Jason.proservice.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
@RequestMapping("/proservice/producer")
public class ProducerController {


    @Autowired
    private ProducerService producerService;

    //查询所有创作者数据
    @GetMapping("findAll")
    public List<Producer> findAllProducer(){
        List<Producer> AllProducerlist = producerService.list(null);
        return AllProducerlist;
    }
    //根据id，逻辑删除讲师
    @DeleteMapping("{id}")
    public boolean deleteByID(@PathVariable String id){
        System.out.println(id);
        boolean b = producerService.removeById(id);
        return b;
    }

}


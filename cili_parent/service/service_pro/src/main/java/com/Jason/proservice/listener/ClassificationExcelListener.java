package com.Jason.proservice.listener;

import com.Jason.proservice.entity.Classification;
import com.Jason.proservice.entity.excel.ClassificationData;
import com.Jason.proservice.service.ClassificationService;
import com.Jason.servicebase.exceptionhandler.CiliException;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class ClassificationExcelListener extends AnalysisEventListener<ClassificationData> {

    public ClassificationService classificationService;

    public ClassificationExcelListener(ClassificationService classificationService){
        this.classificationService = classificationService;

    }
    public ClassificationExcelListener(){};

    //一级分类不能重复添加
    private Classification existLv1(ClassificationService classificationService,String name){
        QueryWrapper<Classification> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        Classification classification = classificationService.getOne(wrapper);
        return classification;
    }
    //二级分类不能重复添加
    private Classification existLv2(ClassificationService classificationService,String name,String pid){
        QueryWrapper<Classification> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        Classification classification = classificationService.getOne(wrapper);
        return classification;
    }

    //读取excel数据，一行一行进行读取
    @Override
    public void invoke(ClassificationData classificationData, AnalysisContext analysisContext) {
        if(classificationData == null){
            throw new CiliException(20001,"文件为空");
        }

        //一行一行读取，每次有两个值，一级分类和二级分类
        //增加一级分类
        Classification classificationLv1 = this.existLv1(classificationService,classificationData.getLv1Classification());
        if(classificationLv1 == null){
            //没有该1级分类，需要添加
            classificationLv1 = new Classification();
            classificationLv1.setParentId("0");
            classificationLv1.setTitle(classificationData.getLv1Classification());
            classificationService.save(classificationLv1);
        }

        //增加二级分类
        String pid = classificationLv1.getId();
        Classification classificationLv2 = this.existLv2(classificationService, classificationData.getLv2Classification(), pid);
        if(classificationLv2 == null){
            //没有该2级分类，需要添加
            classificationLv2 = new Classification();
            classificationLv2.setParentId(pid);
            classificationLv2.setTitle(classificationData.getLv2Classification());
            classificationService.save(classificationLv2);

        }


    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

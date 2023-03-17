package com.Jason.proservice.service.impl;

import com.Jason.proservice.entity.Classification;
import com.Jason.proservice.entity.classification.Lv1Classification;
import com.Jason.proservice.entity.classification.Lv2Classification;
import com.Jason.proservice.entity.excel.ClassificationData;
import com.Jason.proservice.listener.ClassificationExcelListener;
import com.Jason.proservice.mapper.ClassificationMapper;
import com.Jason.proservice.service.ClassificationService;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClassificationServiceImpl extends ServiceImpl<ClassificationMapper, Classification> implements ClassificationService {

    //添加影视分类
    @Override
    public void saveClassification(MultipartFile multipartFile, ClassificationService classificationService) throws IOException {

        InputStream in = multipartFile.getInputStream();
        EasyExcel.read(in, ClassificationData.class,new ClassificationExcelListener(classificationService)).sheet().doRead();;

    }

    //获取全部分类
    @Override
    public List<Lv1Classification> getAllClassification() {
        List<Lv1Classification> resultList = new ArrayList<>();

        //查询所有一级分类
        QueryWrapper<Classification> wrapperLv1 = new QueryWrapper<>();
        wrapperLv1.eq("parent_id","0");
        List<Classification> Lv1ClassificationList = baseMapper.selectList(wrapperLv1);
        //查询所有二级分类
        QueryWrapper<Classification> wrapperLv2 = new QueryWrapper<>();
        wrapperLv2.ne("parent_id","0");
        List<Classification> Lv2ClassificationList = baseMapper.selectList(wrapperLv2);

        //封装一级分类
        for(Classification classification:Lv1ClassificationList){
            Lv1Classification tmp = new Lv1Classification();
            tmp.setId(classification.getId());
            tmp.setTitle(classification.getTitle());
            resultList.add(tmp);

        }
        //封装二级分类
        for(Lv1Classification lv1Classification :resultList){
            String id = lv1Classification.getId();
            for(Classification lv2Class: Lv2ClassificationList){
                Lv2Classification tmp = new Lv2Classification();
                tmp.setId(lv2Class.getId());
                tmp.setTitle(lv2Class.getTitle());
                String pid = lv2Class.getParentId();
                if(pid.equals(id)){
                    lv1Classification.getChildren().add(tmp);
                }
            }
        }

        return resultList;
    }
}

package com.Jason.proservice.service;

import com.Jason.proservice.entity.Classification;
import com.Jason.proservice.entity.classification.Lv1Classification;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ClassificationService extends IService<Classification> {

    //添加课程分类
    void saveClassification(MultipartFile multipartFile, ClassificationService classificationService) throws IOException;

    //获取全部分类，返回结果为一级分类的list，每个一级分类包含一个二级分类的childrenlist
    List<Lv1Classification> getAllClassification();
}

package com.Jason.proservice.service;

import com.Jason.proservice.entity.Classification;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ClassificationService extends IService<Classification> {

    //添加课程分类
    void saveClassification(MultipartFile multipartFile, ClassificationService classificationService) throws IOException;
}

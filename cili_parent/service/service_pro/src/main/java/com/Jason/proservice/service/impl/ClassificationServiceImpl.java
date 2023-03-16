package com.Jason.proservice.service.impl;

import com.Jason.proservice.entity.Classification;
import com.Jason.proservice.entity.excel.ClassificationData;
import com.Jason.proservice.listener.ClassificationExcelListener;
import com.Jason.proservice.mapper.ClassificationMapper;
import com.Jason.proservice.service.ClassificationService;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class ClassificationServiceImpl extends ServiceImpl<ClassificationMapper, Classification> implements ClassificationService {

    //添加影视分类
    @Override
    public void saveClassification(MultipartFile multipartFile, ClassificationService classificationService) throws IOException {

        InputStream in = multipartFile.getInputStream();
        EasyExcel.read(in, ClassificationData.class,new ClassificationExcelListener(classificationService)).sheet().doRead();;

    }
}

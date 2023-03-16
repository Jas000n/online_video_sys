package com.Jason.proservice.controller;

import com.Jason.common.utils.R;
import com.Jason.proservice.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/service/classification")
@CrossOrigin
public class ClassificationController {

    @Autowired
    private ClassificationService classificationService;

    //添加影视分类
    //获取上传过来的文件，读取其中内容
    @PostMapping("addClassification")
    public R addClassification(MultipartFile multipartFile) throws IOException {
        classificationService.saveClassification(multipartFile,classificationService);
        return R.ok();
    }

}

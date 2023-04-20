package com.Jason.proservice.controller;

import com.Jason.common.utils.R;
import com.Jason.proservice.entity.classification.Lv1Classification;
import com.Jason.proservice.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/service/classification")
//@CrossOrigin
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

    //课程分类列表，返回树形数据
    @GetMapping("getAllClassification")
    public R getAllClassification(){

        List<Lv1Classification> result = classificationService.getAllClassification();
        System.out.println(result);
        return R.ok().data("list",result);
    }


}

package com.Jason.ossservice.service.impl;

import com.Jason.ossservice.service.OssService;
import com.Jason.ossservice.utils.ConstPropertiesUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFileAvatar(MultipartFile file) throws IOException {
        String endpoint = ConstPropertiesUtils.END_POINT;
        String accessKeyId = ConstPropertiesUtils.KEY_ID;
        String accessKeySecret = ConstPropertiesUtils.KEY_SECRET;
        String bucketName = ConstPropertiesUtils.BUCKET_NAME;

        //创建oss对象，上传文件流
        OSS ossClient = new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);

        //获取文件流
        InputStream inputStream = file.getInputStream();
        //获取文件名
        String filename = file.getOriginalFilename();

        //在文件名中添加随机并且唯一的值，这样上传同文件名文件不会覆盖
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        filename = uuid+"_"+filename;

        //把文件按照日期进行分类
        String datePath = new DateTime().toString("yyyy/MM/dd/");
        filename = datePath+filename;
        //上传文件
        ossClient.putObject(bucketName,filename,inputStream);
        //关闭ossclient
        ossClient.shutdown();

        String url = "https://"+bucketName+"."+endpoint+"/"+filename;

        return url;

    }
}

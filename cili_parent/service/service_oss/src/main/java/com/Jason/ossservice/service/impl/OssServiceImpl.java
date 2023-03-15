package com.Jason.ossservice.service.impl;

import com.Jason.ossservice.service.OssService;
import com.Jason.ossservice.utils.ConstPropertiesUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

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

        System.out.println(file.getOriginalFilename());

        InputStream inputStream = file.getInputStream();

        String filename = file.getOriginalFilename();

        ossClient.putObject(bucketName,filename,inputStream);

        ossClient.shutdown();

        String url = "https://"+bucketName+"."+endpoint+"/"+filename;

        return url;

    }
}

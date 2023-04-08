package com.Jason.vod.controller;

import com.Jason.common.utils.R;
import com.Jason.vod.service.VodService;
import com.Jason.vod.utils.ConstVodUtils;
import com.Jason.vod.utils.initVodObject;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.vod20170321.AsyncClient;
import com.aliyun.sdk.service.vod20170321.models.GetPlayInfoRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyun.sdk.service.vod20170321.models.*;
import com.aliyun.sdk.service.vod20170321.*;
import com.google.gson.Gson;
import darabonba.core.client.ClientOverrideConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/vod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;
    //上传视频到阿里云
    @PostMapping("uploadAliYunVideo")
    public R uploadAliYunVideo(MultipartFile file) throws IOException {
        String videoId = vodService.uploadAliYunVideo(file);
        return R.ok().data("videoId",videoId);
    }

    //根据id删除episode的id
    @DeleteMapping("removeAliYunVideo/{id}")
    public R removeAliYunVideo(@PathVariable String id) throws ClientException {
        //初始化对象
        DefaultAcsClient client = initVodObject.initVodClient(ConstVodUtils.ACCESS_KEY_ID, ConstVodUtils.ACCESS_KEY_SECRET);
        //初始化删除视频request
        DeleteVideoRequest request = new DeleteVideoRequest();
        //向request里set
        request.setVideoIds(id);
        //发request
        DeleteVideoResponse acsResponse = client.getAcsResponse(request);
        System.out.println(acsResponse);
        return R.ok();
    }

    @GetMapping("getPlayAuth/{episodeVideoSourceId}")
    public R getVideoPlayAuth(@PathVariable("episodeVideoSourceId") String videoId) throws Exception {

        //获取阿里云存储相关常量
        String accessKeyId = ConstVodUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstVodUtils.ACCESS_KEY_SECRET;

        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId(accessKeyId)
                .accessKeySecret(accessKeySecret)
                //.securityToken("<your-token>") // use STS token
                .build());

        // Configure the Client
        AsyncClient client = AsyncClient.builder()
                .region("cn-shanghai") // Region ID
                //.httpClient(httpClient) // Use the configured HttpClient, otherwise use the default HttpClient (Apache HttpClient)
                .credentialsProvider(provider)
                //.serviceConfiguration(Configuration.create()) // Service-level configuration
                // Client-level configuration rewrite, can set Endpoint, Http request parameters, etc.
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("vod.cn-shanghai.aliyuncs.com")
                        //.setConnectTimeout(Duration.ofSeconds(30))
                )
                .build();

        // Parameter settings for API request
        GetPlayInfoRequest getPlayInfoRequest = GetPlayInfoRequest.builder()
                .videoId(videoId)
                // Request-level configuration rewrite, can set Http request parameters, etc.
                // .requestConfiguration(RequestConfiguration.create().setHttpHeaders(new HttpHeaders()))
                .build();

        // Asynchronously get the return value of the API request
        CompletableFuture<GetPlayInfoResponse> response = client.getPlayInfo(getPlayInfoRequest);
        // Synchronously get the return value of the API request
        GetPlayInfoResponse resp = response.get();
        String json = new Gson().toJson(resp);
        System.out.println(json);
        String json222 = new Gson().toJson(response.get().getBody().getPlayInfoList().getPlayInfo().get(0));
        System.out.println(json222);
        JSONObject jsonObject = JSON.parseObject(json222);
        String Url = (String) jsonObject.get("playURL");


//        JSONObject object= JSONObject.parseObject(json);
//        System.out.println(object.getJSONObject("body").getJSONObject("playInfoList").getJSONObject("playInfo").get("playURL"));
//        // Asynchronous processing of return values
        /*response.thenAccept(resp -> {
            System.out.println(new Gson().toJson(resp));
        }).exceptionally(throwable -> { // Handling exceptions
            System.out.println(throwable.getMessage());
            return null;
        });*/

        // Finally, close the client
        client.close();
        return R.ok().message("获取地址成功").data("URL",Url);
    }
}

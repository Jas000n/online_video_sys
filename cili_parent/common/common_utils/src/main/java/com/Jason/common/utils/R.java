package com.Jason.common.utils;
import lombok.Data;
import java.util.HashMap;
import java.util.Map;

/**
 * author  jas0n
 * date    2023/2/28
 */
//统一返回结果

@Data
public class R {
    //是否成功
    private Boolean success;
    //状态码
    private Integer code;
    //返回信息
    private String message;
    //返回数据
    private Map<String, Object> data = new HashMap<String,Object>();

    //构造方法私有化
    private R(){}
    //成功静态方法
    public static R ok(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(20000);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }
    //失败静态方法
    public static R error(){
        R r = new R();
        r.setSuccess(false);
        r.setCode(20000);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }
    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }
    public R message(String message){
        this.setMessage(message);
        return this;
    }
    public R data(String key, Object value){
        this.data.put(key,value);
        return this;
    }
    public R data(Map<String,Object> map){
        this.setData(map);
        return this;
    }
}

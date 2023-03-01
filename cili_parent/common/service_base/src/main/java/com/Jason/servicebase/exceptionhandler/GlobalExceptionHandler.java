package com.Jason.servicebase.exceptionhandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import com.Jason.common.utils.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * author  jas0n
 * date    2023/3/1
 */
//统一处理异常，以免用户看到不友好的信息
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)//指定出现什么异常执行这个方法，这里是全局异常处理
    @ResponseBody//为了返回数据
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行全局异常处理");
    }
}

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

    //特定异常处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理");
    }
    //这里的机制是：先找有没有特定异常对应的handler，如果没有就执行全局异常handler


    //自定义异常
    @ExceptionHandler(CiliException.class)
    @ResponseBody
    public R error(CiliException e){
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}

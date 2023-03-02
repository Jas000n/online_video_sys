package com.Jason.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author  jas0n
 * date    2023/3/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CiliException extends RuntimeException{

    private Integer code;//状态码

    private String msg;//异常信息
}

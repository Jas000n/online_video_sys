package com.Jason.proservice.entity.vo;
//vo代表value object

import lombok.Data;

/**
 * author  jas0n
 * date    2023/3/1
 */
//用于把多条件组合查询的条件传递到接口里面，把查询条件封装到ProducerQuery对象里面，将该对象传递到接口里面
@Data
public class ProducerQuery {
    private String name;

    private String begin;//查询开始时间

    private String end;//查询结束时间

}

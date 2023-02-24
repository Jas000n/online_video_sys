package com.neu.mp_demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neu.mp_demo.entity.User;
import org.springframework.stereotype.Repository;

/**
 * author  jas0n
 * date    2023/2/22
 */
@Repository//此注解为了MpDemoApplicationTests而加
public interface UserMapper extends BaseMapper<User> {
}

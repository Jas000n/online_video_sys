package com.Jason.proservice.mapper;

import com.Jason.proservice.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface CommentMapper extends BaseMapper<Comment> {
}

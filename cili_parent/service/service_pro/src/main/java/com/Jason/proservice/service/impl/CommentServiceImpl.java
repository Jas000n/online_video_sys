package com.Jason.proservice.service.impl;

import com.Jason.proservice.entity.Comment;
import com.Jason.proservice.mapper.CommentMapper;
import com.Jason.proservice.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
}

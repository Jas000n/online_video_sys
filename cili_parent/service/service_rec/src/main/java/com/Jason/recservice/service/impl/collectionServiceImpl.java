package com.Jason.recservice.service.impl;

import com.Jason.recservice.entity.Collection;
import com.Jason.recservice.mapper.collectionMapper;
import com.Jason.recservice.service.collectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Service;

@Service
@MapperScan({"com.Jason.recservice.mapper"})
public class collectionServiceImpl extends ServiceImpl<collectionMapper, Collection> implements collectionService {
}

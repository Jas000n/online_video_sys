package com.Jason.recservice.mapper;


import com.Jason.recservice.entity.Collection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface collectionMapper extends BaseMapper<Collection> {
    public Collection sqlToCsv();
}

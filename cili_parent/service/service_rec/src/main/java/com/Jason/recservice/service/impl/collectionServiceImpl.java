package com.Jason.recservice.service.impl;

import com.Jason.recservice.entity.Collection;
import com.Jason.recservice.mapper.collectionMapper;
import com.Jason.recservice.service.collectionService;
import com.Jason.recservice.utils.WriteCSV;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@MapperScan({"com.Jason.recservice.mapper"})
public class collectionServiceImpl extends ServiceImpl<collectionMapper, Collection> implements collectionService {
    //这里把sql写进mapper了
    @Override
    public void sqlToCsv() {
        baseMapper.sqlToCsv();
    }

    @Override
    public void mysqlDataToCsv() {
        List<Collection> collections = baseMapper.selectList(null);
        for(Collection c:collections){
            System.out.println(c);
        }
        WriteCSV.writeCSV2(collections,"/home/jason/桌面/online_video_sys/cili_parent/service/service_rec/src/main/java/com/Jason/recservice/csvFile/test.csv");
    }
}

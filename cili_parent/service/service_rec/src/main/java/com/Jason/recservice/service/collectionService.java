package com.Jason.recservice.service;

import com.Jason.recservice.entity.Collection;
import com.baomidou.mybatisplus.extension.service.IService;


public interface collectionService extends IService<Collection> {
    //这里导出会有mysql安全问题,需要设置secure-file-priv=""
    public void sqlToCsv();
    //java导出
    public void mysqlDataToCsv();
}

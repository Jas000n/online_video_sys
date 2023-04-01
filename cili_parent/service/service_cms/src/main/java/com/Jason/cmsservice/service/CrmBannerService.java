package com.Jason.cmsservice.service;

import com.Jason.cmsservice.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CrmBannerService extends IService<CrmBanner> {
    //查询所有banner
    List<CrmBanner> getAllBanner();
}

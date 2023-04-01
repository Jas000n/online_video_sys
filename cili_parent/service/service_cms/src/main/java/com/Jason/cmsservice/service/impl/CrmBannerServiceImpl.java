package com.Jason.cmsservice.service.impl;

import com.Jason.cmsservice.entity.CrmBanner;
import com.Jason.cmsservice.mapper.CrmBannerMapper;
import com.Jason.cmsservice.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {
    @Override
    public List<CrmBanner> getAllBanner() {
        List<CrmBanner> crmBanners = baseMapper.selectList(null);
        return crmBanners;
    }
}

package com.Jason.proservice.service;

import com.Jason.proservice.entity.Producer;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 创作者 服务类
 * </p>
 *
 * @author Jason
 * @since 2023-02-28
 */
public interface ProducerService extends IService<Producer> {
    //前台分页查询创作者
    Map<String, Object> getProducerFrontList(Page<Producer> producerPage);
}

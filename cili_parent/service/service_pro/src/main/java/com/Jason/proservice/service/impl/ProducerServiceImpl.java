package com.Jason.proservice.service.impl;

import com.Jason.proservice.entity.Producer;
import com.Jason.proservice.mapper.ProducerMapper;
import com.Jason.proservice.service.ProducerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 创作者 服务实现类
 * </p>
 *
 * @author Jason
 * @since 2023-02-28
 */
@Service
public class ProducerServiceImpl extends ServiceImpl<ProducerMapper, Producer> implements ProducerService {
    //前台分页查询创作者
    @Override
    public Map<String, Object> getProducerFrontList(Page<Producer> producerPage) {
        QueryWrapper<Producer> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        baseMapper.selectPage(producerPage,queryWrapper);

        List<Producer> records = producerPage.getRecords();
        long current = producerPage.getCurrent();
        long pages = producerPage.getPages();
        long size = producerPage.getSize();
        long total = producerPage.getTotal();
        boolean hasNext = producerPage.hasNext();
        boolean hasPrevious =producerPage.hasPrevious();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }
}

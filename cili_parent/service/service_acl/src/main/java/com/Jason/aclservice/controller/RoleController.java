package com.Jason.aclservice.controller;


import com.Jason.aclservice.entity.Role;
import com.Jason.aclservice.service.RoleService;

import com.Jason.common.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  前端控制器
 */
@RestController
@RequestMapping("/admin/acl/role")
//@CrossOrigin
public class RoleController {

    @Autowired
    private RoleService roleService;

   //"获取角色分页列表")
    @GetMapping("{page}/{limit}")
    public R index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            Role role) {
        Page<Role> pageParam = new Page<>(page, limit);
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(role.getRoleName())) {
            wrapper.like("role_name",role.getRoleName());
        }
        roleService.page(pageParam,wrapper);
        return R.ok().data("items", pageParam.getRecords()).data("total", pageParam.getTotal());
    }

    //"获取角色")
    @GetMapping("get/{id}")
    public R get(@PathVariable String id) {
        Role role = roleService.getById(id);
        return R.ok().data("item", role);
    }

    //"新增角色")
    @PostMapping("save")
    public R save(@RequestBody Role role) {
        roleService.save(role);
        return R.ok();
    }

    //"修改角色")
    @PutMapping("update")
    public R updateById(@RequestBody Role role) {
        roleService.updateById(role);
        return R.ok();
    }

    //"删除角色")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        roleService.removeById(id);
        return R.ok();
    }

   //"根据id列表删除角色")
    @DeleteMapping("batchRemove")
    public R batchRemove(@RequestBody List<String> idList) {
        roleService.removeByIds(idList);
        return R.ok();
    }
}


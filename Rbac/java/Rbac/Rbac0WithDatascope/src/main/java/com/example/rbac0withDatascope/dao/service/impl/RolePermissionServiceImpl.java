package com.example.rbac0withDatascope.dao.service.impl;

import com.example.rbac0withDatascope.dao.entity.RolePermission;
import com.example.rbac0withDatascope.dao.service.IRolePermissionService;
import com.example.rbac0withDatascope.dao.mapper.RolePermissionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色权限关联表 服务实现类
 * </p>
 *
 * @author zqh
 * @since 2022-12-01
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

}

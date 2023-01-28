package com.example.rbac0withDatascope.dao.service.impl;

import com.example.rbac0withDatascope.dao.entity.Permission;
import com.example.rbac0withDatascope.dao.mapper.PermissionMapper;
import com.example.rbac0withDatascope.dao.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author zqh
 * @since 2022-12-01
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}

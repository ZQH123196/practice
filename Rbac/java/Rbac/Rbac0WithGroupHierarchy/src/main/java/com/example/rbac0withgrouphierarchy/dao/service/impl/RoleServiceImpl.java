package com.example.rbac0withgrouphierarchy.dao.service.impl;

import com.example.rbac0withgrouphierarchy.dao.entity.Role;
import com.example.rbac0withgrouphierarchy.dao.mapper.RoleMapper;
import com.example.rbac0withgrouphierarchy.dao.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author zqh
 * @since 2022-12-01
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}

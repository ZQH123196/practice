package com.example.rbac0withDatascope.dao.service.impl;

import com.example.rbac0withDatascope.dao.entity.RoleUser;
import com.example.rbac0withDatascope.dao.mapper.RoleUserMapper;
import com.example.rbac0withDatascope.dao.service.IRoleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色用户关联表 服务实现类
 * </p>
 *
 * @author zqh
 * @since 2022-12-01
 */
@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUser> implements IRoleUserService {

}

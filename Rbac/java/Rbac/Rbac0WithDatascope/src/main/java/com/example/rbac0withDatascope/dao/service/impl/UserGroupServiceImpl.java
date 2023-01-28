package com.example.rbac0withDatascope.dao.service.impl;

import com.example.rbac0withDatascope.dao.entity.UserGroup;
import com.example.rbac0withDatascope.dao.service.IUserGroupService;
import com.example.rbac0withDatascope.dao.mapper.UserGroupMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户组表 服务实现类
 * </p>
 *
 * @author zqh
 * @since 2022-12-01
 */
@Service
public class UserGroupServiceImpl extends ServiceImpl<UserGroupMapper, UserGroup> implements IUserGroupService {

}

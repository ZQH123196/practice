package com.example.rbac0withgrouphierarchy.dao.service.impl;

import com.example.rbac0withgrouphierarchy.dao.entity.UserGroupHierarchy;
import com.example.rbac0withgrouphierarchy.dao.mapper.UserGroupHierarchyMapper;
import com.example.rbac0withgrouphierarchy.dao.service.IUserGroupHierarchyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户组继承树 服务实现类
 * </p>
 *
 * @author zqh
 * @since 2022-12-01
 */
@Service
public class UserGroupHierarchyServiceImpl extends ServiceImpl<UserGroupHierarchyMapper, UserGroupHierarchy> implements IUserGroupHierarchyService {

}

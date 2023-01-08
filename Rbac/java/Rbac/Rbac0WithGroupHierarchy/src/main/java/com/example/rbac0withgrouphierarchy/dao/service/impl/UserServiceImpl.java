package com.example.rbac0withgrouphierarchy.dao.service.impl;

import com.example.rbac0withgrouphierarchy.dao.entity.User;
import com.example.rbac0withgrouphierarchy.dao.mapper.UserMapper;
import com.example.rbac0withgrouphierarchy.dao.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zqh
 * @since 2022-12-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}

package com.example.rbac0withresource.dao.service.impl;

import com.example.rbac0withresource.dao.entity.User;
import com.example.rbac0withresource.dao.mapper.UserMapper;
import com.example.rbac0withresource.dao.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zqh
 * @since 2022-11-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}

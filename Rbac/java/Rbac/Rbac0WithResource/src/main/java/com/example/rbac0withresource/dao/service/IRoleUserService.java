package com.example.rbac0withresource.dao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rbac0withresource.dao.entity.RoleUser;

import java.util.Set;

public interface IRoleUserService extends IService<RoleUser> {
    Set<String> getRolesByUserName(String username);
}

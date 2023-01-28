package com.example.rbac0withDatascope.datascope.servletconf.webcontext;

import com.example.rbac0withDatascope.dao.entity.Role;
import com.example.rbac0withDatascope.dao.entity.User;
import com.example.rbac0withDatascope.dao.entity.UserGroup;
import lombok.Data;

import java.util.Set;

@Data
public class UserContext {
    private User user;
    private UserGroup userGroup;
    private boolean datascopeFlag = false;
    /**
     * 保存该用户相关的继承树结构
     * 一个用户可以被分配到多个用户组
     * 并且可能存在两个无关联的兄弟节点，所以是一个 Set
     */
    private Set<User> manageUserPerm;
    /**
     * 作为扩展点使用，可以自定义 pattern 的内容来实现扩展
     */
    private String datascopePattern;
    private Set<Role> roles;

}

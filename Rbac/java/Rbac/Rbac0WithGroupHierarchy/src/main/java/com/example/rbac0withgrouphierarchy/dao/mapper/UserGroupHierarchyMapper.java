package com.example.rbac0withgrouphierarchy.dao.mapper;

import com.example.rbac0withgrouphierarchy.dao.entity.UserGroupHierarchy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户组继承树 Mapper 接口
 * </p>
 *
 * @author zqh
 * @since 2022-12-01
 */
@Mapper
public interface UserGroupHierarchyMapper extends BaseMapper<UserGroupHierarchy> {

}

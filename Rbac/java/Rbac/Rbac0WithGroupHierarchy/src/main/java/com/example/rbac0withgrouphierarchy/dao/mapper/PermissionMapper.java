package com.example.rbac0withgrouphierarchy.dao.mapper;

import com.example.rbac0withgrouphierarchy.dao.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author zqh
 * @since 2022-12-01
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}

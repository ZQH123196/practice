package com.example.rbac0withgrouphierarchy.dao.mapper;

import com.example.rbac0withgrouphierarchy.dao.entity.RoleUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色用户关联表 Mapper 接口
 * </p>
 *
 * @author zqh
 * @since 2022-12-01
 */
@Mapper
public interface RoleUserMapper extends BaseMapper<RoleUser> {

}

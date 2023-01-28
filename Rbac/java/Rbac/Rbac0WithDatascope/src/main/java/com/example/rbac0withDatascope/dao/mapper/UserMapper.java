package com.example.rbac0withDatascope.dao.mapper;

import com.example.rbac0withDatascope.dao.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author zqh
 * @since 2022-12-01
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

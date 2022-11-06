package com.example.rbac0withresource.dao.mapper;

import com.example.rbac0withresource.dao.entity.ResourcePermel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 常规资源表（即不需要额外处理的资源）,该资源只用于表达权限，使用起来相当于写死权限，将与代码深度耦合 Mapper 接口
 * </p>
 *
 * @author zqh
 * @since 2022-11-06
 */
@Mapper
public interface ResourcePermelMapper extends BaseMapper<ResourcePermel> {

}

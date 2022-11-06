package com.example.rbac0withresource.dao.service;

import com.example.rbac0withresource.dao.entity.ResourcePermel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 常规资源表（即不需要额外处理的资源）,该资源只用于表达权限，使用起来相当于写死权限，将与代码深度耦合 服务类
 * </p>
 *
 * @author zqh
 * @since 2022-11-06
 */
public interface IResourcePermelService extends IService<ResourcePermel> {

}

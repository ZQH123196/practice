package com.example.rbac0withDatascope.Initial;

import com.example.rbac0withDatascope.service.IDatascope4UserGroupService;
import com.example.rbac0withDatascope.service.vo.MultipleUserGroupTree;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 启动时将会从数据库初始化加载所有 UserGroupHierarchy 的数据，并以编程语言的 treeNode 概念存在
 * 这样就可以抽象掉数据库树实现的多种多样。
 * 监听 UserGroupHierarchy 的变更，一旦发生值的设置，就更新所有机子的 GlobalUserGroupHierarchy。
 * 触发对应的 controller 来进行更新，同时要通过注册中心广播到所有机子的更新接口。
 *
 */
@Component
@Slf4j
public class GlobalUserGroupHierarchy implements BeanPostProcessor {
    @Resource
    private IDatascope4UserGroupService userGroupDatascopeService;

    @Getter
    private MultipleUserGroupTree multipleUserGroupTree;

    /**
     * 初始化，从数据库取数
     * @param bean the new bean instance
     * @param beanName the name of the bean
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        multipleUserGroupTree = userGroupDatascopeService.getAllMultipleTree();
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}

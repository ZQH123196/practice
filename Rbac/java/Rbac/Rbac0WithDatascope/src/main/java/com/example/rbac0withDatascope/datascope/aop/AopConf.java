package com.example.rbac0withDatascope.datascope.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.Resource;

/**
 * 只要注册进 aop 容器，就能自动生效
 */
@Configuration
@EnableAspectJAutoProxy
public class AopConf {

    @Resource
    UserGroupDatascopeAdvice userGroupDatascopeAdvice;

    @Resource
    UserGroupDatascopePointcut userGroupDatascopePointcut;

    @Bean
    public UserGroupDatascopeAdvisor userGroupDatascopeAdvisor() {
        UserGroupDatascopeAdvisor userGroupDatascopeAdvisor = new UserGroupDatascopeAdvisor();
        userGroupDatascopeAdvisor.setAdvice(userGroupDatascopeAdvice);
        userGroupDatascopeAdvisor.setLogPointCut(userGroupDatascopePointcut);
        return userGroupDatascopeAdvisor;
    }
}

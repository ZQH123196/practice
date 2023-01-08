package com.example.rbac0withgrouphierarchy.datascope.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AopConf {

    @Bean
    public UserGroupDatascopeAdvisor userGroupDatascopeAdvisor() {
        UserGroupDatascopeAdvice userGroupDatascopeAdvice = new UserGroupDatascopeAdvice();
        UserGroupDatascopePointcut userGroupDatascopePointcut = new UserGroupDatascopePointcut();

        UserGroupDatascopeAdvisor userGroupDatascopeAdvisor = new UserGroupDatascopeAdvisor();
        userGroupDatascopeAdvisor.setAdvice(userGroupDatascopeAdvice);
        userGroupDatascopeAdvisor.setLogPointCut(userGroupDatascopePointcut);
        return userGroupDatascopeAdvisor;
    }
}

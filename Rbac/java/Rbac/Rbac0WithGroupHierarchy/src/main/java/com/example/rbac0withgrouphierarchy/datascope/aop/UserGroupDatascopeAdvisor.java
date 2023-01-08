package com.example.rbac0withgrouphierarchy.datascope.aop;

import lombok.Setter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

public class UserGroupDatascopeAdvisor extends AbstractBeanFactoryPointcutAdvisor implements BeanPostProcessor {
    @Setter
    private Pointcut logPointCut;

    @Override
    public Pointcut getPointcut() {
        return logPointCut;
    }
}

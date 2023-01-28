package com.example.rbac0withDatascope.datascope.aop;

import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 *
 * RegexpMethodPointcutAdvisor
 * AbstractBeanFactoryPointcutAdvisor
 */
public class UserGroupDatascopeAdvisor extends AbstractBeanFactoryPointcutAdvisor {
    @Setter
    private Pointcut logPointCut;

    @NotNull
    @Override
    public Pointcut getPointcut() {
        return logPointCut;
    }
}

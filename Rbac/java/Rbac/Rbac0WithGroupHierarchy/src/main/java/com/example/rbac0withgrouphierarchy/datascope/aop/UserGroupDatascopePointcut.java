package com.example.rbac0withgrouphierarchy.datascope.aop;

import com.example.rbac0withgrouphierarchy.datascope.annotation.UserGroupDatascope;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class UserGroupDatascopePointcut extends StaticMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        // 此方法可以查找继承关系的注解，
        // 接口上的之所以不生效就是因为实现类不继承接口上的注解并且也没有选择自定义递归向上查找注解的这种 api
        return AnnotatedElementUtils.hasAnnotation(method, UserGroupDatascope.class);
    }
}

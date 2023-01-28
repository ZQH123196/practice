package com.example.rbac0withDatascope.datascope.aop;

import com.example.rbac0withDatascope.datascope.annotation.Datascope;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class UserGroupDatascopePointcut extends StaticMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        // AnnotatedElementUtils.hasAnnotation 此方法可以查找继承关系的注解，
        // 接口上的之所以不生效就是因为实现类不继承接口上的注解,并且也没有使用递归向上查找注解的这种 api
        // 这里判断只有父类或实现类拥有的注解都返回 ture
        return AnnotatedElementUtils.hasAnnotation(method, Datascope.class);
    }
}

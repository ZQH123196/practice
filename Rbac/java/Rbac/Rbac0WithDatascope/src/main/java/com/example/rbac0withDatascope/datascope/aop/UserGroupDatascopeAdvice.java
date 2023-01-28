package com.example.rbac0withDatascope.datascope.aop;

import com.example.rbac0withDatascope.datascope.servletconf.webcontext.PerThreadUserContext;
import com.example.rbac0withDatascope.datascope.servletconf.webcontext.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

/**
 * 将 datascopeFlag 设置为 true
 */
@Slf4j
@Component
public class UserGroupDatascopeAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        UserContext userContext = PerThreadUserContext.getUserContext().get();
        if (userContext != null) {
            userContext.setDatascopeFlag(true);
        }

        return invocation.proceed();
    }
}

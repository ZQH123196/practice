package com.example.rbac0withDatascope.datascope.servletconf.webcontext;

import org.springframework.stereotype.Component;

@Component
public class PerThreadUserContext {
    private final static ThreadLocal<UserContext> userContext = new ThreadLocal<>();

    public static ThreadLocal<UserContext> getUserContext() {
        return userContext;
    }
}

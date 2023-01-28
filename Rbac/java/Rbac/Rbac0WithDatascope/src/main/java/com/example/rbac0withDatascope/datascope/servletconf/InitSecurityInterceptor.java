package com.example.rbac0withDatascope.datascope.servletconf;

import com.example.rbac0withDatascope.dao.entity.User;
import com.example.rbac0withDatascope.datascope.servletconf.webcontext.PerThreadUserContext;
import com.example.rbac0withDatascope.datascope.servletconf.webcontext.UserContext;
import com.example.rbac0withDatascope.service.IUserGroupDatascopeService;
import com.example.rbac0withDatascope.service.vo.TreeNodeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;


@Component
@Slf4j
public class InitSecurityInterceptor implements HandlerInterceptor {


    @Value("${auth.http.head.loginName}")
    private String userNameHead;

    @Value("${auth.http.head.loginPass}")
    private String userPasswordHead;


    @Resource
    private IUserGroupDatascopeService userGroupDatascopeService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        initUserContext(request);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        resetUserContext();
    }



    private void initUserContext(HttpServletRequest request) {
        User user = getUserByReq(request);
        Set<TreeNodeVo> treeNodeVo = userGroupDatascopeService.getUserGroupHierarchyByUserId(user.getId());
        UserContext userContext = new UserContext();
        userContext.setUser(user);
        userContext.getManageUserPerm().addAll(treeNodeVo);
        PerThreadUserContext.getUserContext().set(userContext);
    }

    private User getUserByReq(HttpServletRequest request) {
        String userName = request.getHeader(userNameHead);
        String userPassword = request.getHeader(userPasswordHead);

        Assert.notNull(userName, "userName 不可为空！");
        Assert.notNull(userPassword, "userPassword 不可为空！");
        return User
                .builder()
                .username(userName)
                .password(userPassword)
                .build();
    }

    private static void resetUserContext() {
        // 清理 threadlocal
        PerThreadUserContext.getUserContext().remove();
    }

}

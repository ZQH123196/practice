package com.example.rbac0withDatascope.datascope.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.IDialect;
import com.example.rbac0withDatascope.dao.entity.User;
import com.example.rbac0withDatascope.dao.entity.UserUsergroup;
import com.example.rbac0withDatascope.dao.service.IUserUsergroupService;
import com.example.rbac0withDatascope.datascope.servletconf.webcontext.PerThreadUserContext;
import com.example.rbac0withDatascope.datascope.servletconf.webcontext.UserContext;
import com.example.rbac0withDatascope.service.IUserGroupDatascopeService;
import com.example.rbac0withDatascope.service.vo.TreeNodeVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class DatascopeInterceptor implements InnerInterceptor {

    @Resource
    IUserUsergroupService userUsergroupService;

    @Resource
    IUserGroupDatascopeService userGroupDatascopeService;

    /**
     * 数据库类型
     * <p>
     * 查看 findIDialect(Executor) 逻辑
     */
    private DbType dbType;
    /**
     * 方言实现类
     * <p>
     * 查看 findIDialect(Executor) 逻辑
     */
    private IDialect dialect;

    public DatascopeInterceptor(DbType dbType) {
        this.dbType = dbType;
    }

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {

        UserContext userContext = PerThreadUserContext.getUserContext().get();
        if (userContext != null && userContext.isDatascopeFlag()) {

            Set<User> manageUserPerm = userContext.getManageUserPerm();
            if (manageUserPerm == null) {
                // 先关掉 flag，避免函数内部又进入这个判定
                userContext.setDatascopeFlag(false);
                setManageUserPerm(userContext);
                userContext.setDatascopeFlag(true);
            }

            String rawSql = boundSql.getSql();
            String datascopeSql = addDatascopeLimit2Sql(rawSql);

            PluginUtils.MPBoundSql mpBoundSql = PluginUtils.mpBoundSql(boundSql);
            Map<String, Object> additionalParameter = mpBoundSql.additionalParameters();
            log.debug("datascope sql final result: [{}]", datascopeSql);

            // 设置最终 sql
            mpBoundSql.sql(datascopeSql);
        }

    }

    private void setManageUserPerm(UserContext userContext) {
        User user = userContext.getUser();
        HashSet<User> set = new HashSet<>();
        // 1、查询该用户拥有那些组
        LambdaQueryWrapper<UserUsergroup> query = Wrappers.<UserUsergroup>lambdaQuery();
        List<UserUsergroup> userUsergroups = userUsergroupService.list(query);
        // 2、根据用户组获取其继承关系下级的所有用户组，并保存
        Set<Integer> userGroupIdSet = new HashSet<>();

        for (UserUsergroup userUsergroup : userUsergroups) {
            List<TreeNodeVo> treeNodeVos = userGroupDatascopeService.getTargetNodeChildren(userUsergroup.getUserGroupId());
            // TODO walkNode4lambda
//            userGroupIdSet.addAll(treeNodeVos);
        }
        // 3、根据这些用户组，获取其包含的全部用户

        userContext.setManageUserPerm(set);
    }

    private String addDatascopeLimit2Sql(String rawSql) {
        return rawSql;
    }
}

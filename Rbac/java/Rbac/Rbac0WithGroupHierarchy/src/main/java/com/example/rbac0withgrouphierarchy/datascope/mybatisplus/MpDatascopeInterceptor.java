package com.example.rbac0withgrouphierarchy.datascope.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.IDialect;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;
import java.util.Map;

public class MpDatascopeInterceptor implements InnerInterceptor {
    /**
     * 数据库类型
     *
     * 查看 findIDialect(Executor) 逻辑
     */
    private DbType dbType;
    /**
     * 方言实现类
     *
     * 查看 findIDialect(Executor) 逻辑
     */
    private IDialect dialect;

    public MpDatascopeInterceptor(DbType dbType) {
        this.dbType = dbType;
    }

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {

        String rawSql = boundSql.getSql();

        String datascopeSql = addDatascopeLimit2Sql(rawSql);

        PluginUtils.MPBoundSql mpBoundSql = PluginUtils.mpBoundSql(boundSql);
        Map<String, Object> additionalParameter = mpBoundSql.additionalParameters();
        mpBoundSql.sql(datascopeSql);
    }

    private String addDatascopeLimit2Sql(String rawSql) {
        return rawSql;
    }
}

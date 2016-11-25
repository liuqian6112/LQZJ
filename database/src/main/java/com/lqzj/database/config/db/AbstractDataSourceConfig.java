package com.lqzj.database.config.db;

import com.lqzj.common.properties.DataSourceProperties;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

public abstract class AbstractDataSourceConfig {
    private DataSourceProperties dataSourceProperties;

    public void setDataSourceProperties(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    protected abstract void init();

    protected abstract DataSource dataSource();

    public abstract PlatformTransactionManager transactionManager();

    public abstract SqlSessionFactory sqlSessionFactory() throws Exception;

    protected void setDataSourceConfig(ComboPooledDataSource dataSource) throws PropertyVetoException {
        dataSource.setDriverClass(dataSourceProperties.getDriverClassName());
        dataSource.setJdbcUrl(getJdbcUrl());
        dataSource.setUser(dataSourceProperties.getUser());
        dataSource.setPassword(dataSourceProperties.getPassword());
        dataSource.setMaxPoolSize(dataSourceProperties.getMaxPoolSize());
        dataSource.setMinPoolSize(dataSourceProperties.getMinPoolSize());
        dataSource.setInitialPoolSize(dataSourceProperties.getInitialPoolSize());
        dataSource.setAcquireIncrement(dataSourceProperties.getAcquireIncrement());
        dataSource.setPreferredTestQuery(dataSourceProperties.getPreferredTestQuery());
        dataSource.setIdleConnectionTestPeriod(dataSourceProperties.getIdleConnectionTestPeriod());
        dataSource.setNumHelperThreads(dataSourceProperties.getNumHelperThreads());
    }

    private String getJdbcUrl() {
        return "jdbc:mysql://" + dataSourceProperties.getHost() + ":" + dataSourceProperties.getPort()
                + "/" + dataSourceProperties.getSchema() + "?useUnicode=true&characterEncoding="
                + dataSourceProperties.getCharacterEncoding() + "&allowMultiQueries=true";
    }

    protected DataSourceTransactionManager buildTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    protected SqlSessionFactory buildSqlSessionFactory(String mapperLocations, String configLocation) throws
            Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(mapperLocations));
        sqlSessionFactoryBean
                .setConfigLocation(new DefaultResourceLoader().getResource(configLocation));
        return sqlSessionFactoryBean.getObject();
    }
}

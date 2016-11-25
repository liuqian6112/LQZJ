package com.lqzj.database.config.db;

import com.lqzj.common.properties.DataSourceProperties;
import com.lqzj.common.properties.MybatisProperties;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@EnableTransactionManagement
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
@AutoConfigureAfter({DataSourceProperties.class, MybatisProperties.class})
@MapperScan(basePackages = {"com.lqzj.**.dao"})
public class DataSourceConfig extends AbstractDataSourceConfig implements TransactionManagementConfigurer {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Autowired
    private MybatisProperties mybatisProperties;

    @PostConstruct
    @Override
    protected void init() {
        setDataSourceProperties(dataSourceProperties);
    }

    @Bean(destroyMethod = "close")
    @Primary
    @Override
    protected DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            setDataSourceConfig(dataSource);
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

        return dataSource;
    }

    @Override
    public PlatformTransactionManager transactionManager() {
        return buildTransactionManager();
    }

    @Override
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        return buildSqlSessionFactory(mybatisProperties.getMapperLocations(), mybatisProperties.getConfigLocation());
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManager();
    }
}

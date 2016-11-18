package com.lqzj.database.config.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application-db.properties")
public class DataSourceConfig {

    private static final String PREFIX = "spring.datasource";

    @Bean
    @ConfigurationProperties(prefix = DataSourceConfig.PREFIX)
    public DataSource dataSource() {
        return new org.apache.tomcat.jdbc.pool.DataSource();
    }
}

package com.lqzj.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-db.properties")
@ConfigurationProperties(prefix = DataSourceProperties.PREFIX)
public class DataSourceProperties {
    static final String PREFIX = "datasource";

    private String host;
    private Integer port;
    private String schema;
    private String characterEncoding;
    private String driverClassName;
    private String user;
    private String password;
    private Integer minPoolSize;
    private Integer maxPoolSize;
    private Integer initialPoolSize;
    private Integer maxIdleTime;
    private Integer acquireIncrement;
    private String preferredTestQuery;
    private Integer idleConnectionTestPeriod;
    private Integer numHelperThreads;
    private Boolean initMigrate;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getCharacterEncoding() {
        return characterEncoding;
    }

    public void setCharacterEncoding(String characterEncoding) {
        this.characterEncoding = characterEncoding;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMinPoolSize() {
        return minPoolSize;
    }

    public void setMinPoolSize(Integer minPoolSize) {
        this.minPoolSize = minPoolSize;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public Integer getInitialPoolSize() {
        return initialPoolSize;
    }

    public void setInitialPoolSize(Integer initialPoolSize) {
        this.initialPoolSize = initialPoolSize;
    }

    public Integer getMaxIdleTime() {
        return maxIdleTime;
    }

    public void setMaxIdleTime(Integer maxIdleTime) {
        this.maxIdleTime = maxIdleTime;
    }

    public Integer getAcquireIncrement() {
        return acquireIncrement;
    }

    public void setAcquireIncrement(Integer acquireIncrement) {
        this.acquireIncrement = acquireIncrement;
    }

    public String getPreferredTestQuery() {
        return preferredTestQuery;
    }

    public void setPreferredTestQuery(String preferredTestQuery) {
        this.preferredTestQuery = preferredTestQuery;
    }

    public Integer getIdleConnectionTestPeriod() {
        return idleConnectionTestPeriod;
    }

    public void setIdleConnectionTestPeriod(Integer idleConnectionTestPeriod) {
        this.idleConnectionTestPeriod = idleConnectionTestPeriod;
    }

    public Integer getNumHelperThreads() {
        return numHelperThreads;
    }

    public void setNumHelperThreads(Integer numHelperThreads) {
        this.numHelperThreads = numHelperThreads;
    }

    public Boolean getInitMigrate() {
        return initMigrate;
    }

    public void setInitMigrate(Boolean initMigrate) {
        this.initMigrate = initMigrate;
    }
}

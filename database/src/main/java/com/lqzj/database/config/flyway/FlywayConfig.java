package com.lqzj.database.config.flyway;

import com.lqzj.database.config.db.DataSourceConfig;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@AutoConfigureAfter(DataSourceConfig.class)
public class FlywayConfig {

    protected Flyway flyway;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initFlyway() {
        flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setBaselineOnMigrate(true);
        flyway.setLocations("db.migration");
        flyway.setEncoding("UTF-8");
        flyway.setOutOfOrder(true);
    }

    @Bean
    public Flyway flyway() {
        flyway.repair();
        flyway.migrate();
        return flyway;
    }
}

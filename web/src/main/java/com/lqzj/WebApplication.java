package com.lqzj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WebApplication {

    private static final Logger LOG = LoggerFactory.getLogger(WebApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);

        LOG.info("max memory:{} MB", Runtime.getRuntime().maxMemory() / 1024 / 1024);
        LOG.info("total memory:{} MB", Runtime.getRuntime().totalMemory() / 1024 / 1024);
        LOG.info("free memory:{} MB", Runtime.getRuntime().freeMemory() / 1024 / 1024);
        LOG.info("used memory:{} MB",
                (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024);
    }

}

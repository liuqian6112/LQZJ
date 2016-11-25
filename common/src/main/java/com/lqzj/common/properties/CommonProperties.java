package com.lqzj.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:application-config.properties"})
@ConfigurationProperties(prefix = CommonProperties.PREFIX)
public class CommonProperties {
    static final String PREFIX = "app";

    private String frameOptions;
    private String allowOrigin;
    private String allowCredentials;
    private String allowMethod;
    private String maxAges;
    private String allowHeaders;
    private String cacheControl;
    private String pragma;

    public String getFrameOptions() {
        return frameOptions;
    }

    public void setFrameOptions(String frameOptions) {
        this.frameOptions = frameOptions;
    }

    public String getAllowOrigin() {
        return allowOrigin;
    }

    public void setAllowOrigin(String allowOrigin) {
        this.allowOrigin = allowOrigin;
    }

    public String getAllowCredentials() {
        return allowCredentials;
    }

    public void setAllowCredentials(String allowCredentials) {
        this.allowCredentials = allowCredentials;
    }

    public String getAllowMethod() {
        return allowMethod;
    }

    public void setAllowMethod(String allowMethod) {
        this.allowMethod = allowMethod;
    }

    public String getMaxAges() {
        return maxAges;
    }

    public void setMaxAges(String maxAges) {
        this.maxAges = maxAges;
    }

    public String getAllowHeaders() {
        return allowHeaders;
    }

    public void setAllowHeaders(String allowHeaders) {
        this.allowHeaders = allowHeaders;
    }

    public String getCacheControl() {
        return cacheControl;
    }

    public void setCacheControl(String cacheControl) {
        this.cacheControl = cacheControl;
    }

    public String getPragma() {
        return pragma;
    }

    public void setPragma(String pragma) {
        this.pragma = pragma;
    }
}

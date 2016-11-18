package com.lqzj.common.filter;

import com.lqzj.common.config.CommonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ResponseAdditionalFilter implements Filter {

    @Autowired
    private CommonProperties properties;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("X-Frame-Options", properties.getFrameOptions());
        response.setHeader("Access-Control-Allow-Origin", properties.getAllowOrigin());
        response.setHeader("Access-Control-Allow-Credentials", properties.getAllowCredentials());
        response.setHeader("Access-Control-Allow-Methods", properties.getAllowMethod());
        response.setHeader("Access-Control-Max-Age", properties.getMaxAges());
        response.setHeader("Access-Control-Allow-Headers", properties.getAllowHeaders());
        response.setHeader("Cache-Control", properties.getCacheControl());
        response.setHeader("Pragma", properties.getPragma());
        filterChain.doFilter(servletRequest, response);
    }

    @Override
    public void destroy() {

    }
}

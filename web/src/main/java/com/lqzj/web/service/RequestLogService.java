package com.lqzj.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class RequestLogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLogService.class);

    public void log(HttpServletRequest request, String payload) {
        final String httpMethod = request.getMethod();
        final String requestURI = request.getRequestURI();
        final String remoteIP = request.getRemoteAddr();
        final String itCode = getItCodeFromRequest(request);
        this.log(requestURI, httpMethod, remoteIP, itCode, payload);
    }

    private void log(String url, String method, String remoteIP, String itCode, String payload) {
        LOGGER.info("request url: {}, method: {}, remoteIP: {}, itCode: {}, payload: {}",
                url, method, remoteIP, itCode, payload);
    }

    private String getItCodeFromRequest(HttpServletRequest request) {
        return " from request ";
    }
}

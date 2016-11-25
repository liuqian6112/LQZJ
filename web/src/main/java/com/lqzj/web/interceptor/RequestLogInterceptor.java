package com.lqzj.web.interceptor;

import com.lqzj.common.annotation.RequestLogger;
import com.lqzj.web.service.RequestLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Configuration
public class RequestLogInterceptor extends WebMvcConfigurerAdapter {

    @Autowired
    private RequestLogService logService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getHandlerInterceptorAdapter());
        super.addInterceptors(registry);
    }

    private HandlerInterceptor getHandlerInterceptorAdapter() {
        return new HandlerInterceptorAdapter() {
            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response,
                                   Object handler, ModelAndView modelAndView) throws Exception {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                Method method = handlerMethod.getMethod();

                if (method.isAnnotationPresent(RequestLogger.class)) {
                    logRequest(request);
                }
            }
        };
    }

    private void logRequest(HttpServletRequest request) {
        logService.log(request, "");
    }

}

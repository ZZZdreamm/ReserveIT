package com.zzzdream.springreserve.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Component
public class GlobalPreAuthorizeInterceptor extends HandlerInterceptorAdapter {

    private final RequestMappingHandlerMapping handlerMapping;

    @Autowired
    public GlobalPreAuthorizeInterceptor(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            PreAuthorize preAuthorize = AnnotationUtils.findAnnotation(method, PreAuthorize.class);
            if (preAuthorize == null && request.getMethod().equalsIgnoreCase("GET")) {
//                applyDefaultPreAuthorize(handlerMethod);
            }
        }
        return true;
    }

//    private void applyDefaultPreAuthorize(HandlerMethod handlerMethod) {
//        Method method = handlerMethod.getMethod();
//        PreAuthorize defaultPreAuthorize = AnnotationUtils.findAnnotation(method.getDeclaringClass(), PreAuthorize.class);
//        if (defaultPreAuthorize != null) {
//            String defaultExpression = defaultPreAuthorize.value();
//            PreAuthorize newPreAuthorize = new PreAuthorize() {
//                @Override
//                public String value() {
//                    return defaultExpression;
//                }
//
//                @Override
//                public Class<? extends Annotation> annotationType() {
//                    return PreAuthorize.class;
//                }
//
//            };
//
//        }
//    }
}

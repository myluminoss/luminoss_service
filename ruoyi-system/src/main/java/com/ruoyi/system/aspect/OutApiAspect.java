package com.ruoyi.system.aspect;


import com.ruoyi.system.annotation.OutApi;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class OutApiAspect {


    @Around(value = "@annotation(outApi)", argNames = "joinPoint,outApi")
    public Object checkAuth(ProceedingJoinPoint joinPoint, OutApi outApi) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new RuntimeException("");
        }
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("Authorization");
        if (token == null || !token.equals("valid_token")) {
            throw new RuntimeException("");
        }
        return joinPoint.proceed();
    }

}

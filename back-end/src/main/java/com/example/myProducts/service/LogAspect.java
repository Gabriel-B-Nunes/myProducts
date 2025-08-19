package com.example.myProducts.service;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Aspect
@Component
public class LogAspect {
    
    //variables
    private final ObjectMapper mapper = new ObjectMapper()
        .registerModule(new JavaTimeModule());
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //methods
    @Pointcut("execution(* com.example.myProducts.controller..*(..))")
    public void pointCut() {}

    @Before("pointCut()")
    public void logMethodBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getName();
        Object[] args = joinPoint.getArgs();
        
        try {
            logger.info("==> method(s): {}, arguments: {} ", methodName, mapper.writeValueAsString(args));
        } catch (Exception e) {
            logger.error("Error while converting", e);
        }
    }

    @AfterReturning(pointcut = "pointCut()", returning = "entity")
    public void logMethodAfter(JoinPoint joinPoint, ResponseEntity<?> entity) {
        try {
            logger.info("==> response: {} ", mapper.writeValueAsString(entity));
        }   catch (Exception e) {
            logger.error("Error while converting", e);
        }
    }

}

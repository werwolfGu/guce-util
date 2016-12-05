package com.guce.aop;

import com.guce.annotation.Action;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by chengen.gu on 2016/12/2.
 */
@Aspect
@Component
public class LogAspect {

    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(com.guce.annotation.Action)")
    public void anntotaionPointcut(){
    }



    @After("anntotaionPointcut()")
    public void after(JoinPoint joinPoint){

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);

        logger.info("注解拦截：{}", action.name());
    }


    @Before("execution( * com.guce.aop.DemoAnnotationService.*(..))")
    public void before(JoinPoint joinPoint){

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        logger.info("拦截方法：{}" ,method.getName());
        Action action = method.getAnnotation(Action.class);

        logger.info("注解拦截：{}", action.name());
    }
}

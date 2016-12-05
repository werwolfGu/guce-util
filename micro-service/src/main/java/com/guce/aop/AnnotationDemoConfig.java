package com.guce.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by chengen.gu on 2016/12/3.
 */
@Configuration
@ComponentScan("com.guce.aop")
@EnableAspectJAutoProxy    //开启对  Aspect 注解的支持
public class AnnotationDemoConfig {
}

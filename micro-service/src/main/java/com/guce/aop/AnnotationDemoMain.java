package com.guce.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by chengen.gu on 2016/12/3.
 */
public class AnnotationDemoMain {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotationDemoConfig.class);
        DemoAnnotationService demoAnnotationService =  context.getBean(DemoAnnotationService.class);
        demoAnnotationService.add();
    }
}

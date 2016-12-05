package com.guce.aware;

import com.guce.annotation.ScannContextConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chengen.gu on 2016/12/3.
 */
//@Configuration
@ScannContextConfig
public class AwareDemoMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.guce.aware");
        AwareServiceDemo demo = context.getBean(AwareServiceDemo.class);
        demo.outputResult();
    }
}

package com.guce;


import com.guce.aop.AnnotationDemoConfig;
import com.guce.aop.DemoAnnotationService;
import com.guce.common.AnnoScannerTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by chengen.gu on 2016/12/2.
 */
public class SpringbootMain {

    private static Logger logger = LoggerFactory.getLogger(SpringbootMain.class);

    public static void main(String[] args) {
        logger.info("log print test {}","打印出日志了");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(GuConfig.class);

        AnnoScannerTest annoScannerTest = (AnnoScannerTest) context.getBean("annoScannerTest");
        String str = annoScannerTest.sayHello("顾呈恩");
        logger.info(str);


    }
}

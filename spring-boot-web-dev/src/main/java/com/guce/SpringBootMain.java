package com.guce;

import com.guce.vo.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Created by chengen.gu on 2016/12/4.
 */
@SpringBootApplication
//@EnableConfigurationProperties(Person.class)
public class SpringBootMain {

    private static Logger logger = LoggerFactory.getLogger(SpringBootMain.class);
    public static void main(String[] args) {
        logger.warn("#############启动spring boot################");
        SpringApplication context = new SpringApplication(SpringBootMain.class);
        context.setBannerMode(Banner.Mode.OFF);
        context.run(args);
    }
}

package com.guce.webmvc;

import com.guce.webmvc.vo.AutorVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


/**
 * Created by chengen.gu on 2016/12/3.
 */
@EnableConfigurationProperties(AutorVo.class)
@SpringBootApplication
public class SpringBootMain {

    private static Logger logger = LoggerFactory.getLogger(SpringBootMain.class);

    public static void main(String[] args) {

        SpringApplication.run(SpringBootMain.class,args);
    }
}

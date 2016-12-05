package com.guce.webmvc.controller;

import com.guce.webmvc.vo.AutorVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chengen.gu on 2016/12/3.
 */
@RestController
public class AnnoDemoController {
    private static Logger logger = LoggerFactory.getLogger(AnnoDemoController.class);

    @Autowired
    private AutorVo autorVo;

    @RequestMapping("/test")
    public String testDemoMethod(){
        logger.info("进入到方法中");
        return "url: can access" + autorVo.getName() + ":" + autorVo.getAge();
    }

    /*public static void main(String[] args) {
        SpringApplication.run(AnnoDemoController.class,args);
    }*/
}

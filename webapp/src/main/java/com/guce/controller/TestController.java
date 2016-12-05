package com.guce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/11/27.
 */
@Controller
@RequestMapping(value="/guce/test")
public class TestController {

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    public String queryTestInfo(HttpServletRequest request){

        return null;
    }
}

package com.guce.common;

import org.springframework.stereotype.Component;

/**
 * Created by chengen.gu on 2016/12/2.
 */
@Component("annoScannerTest")
public class AnnoScannerTest {

    public String sayHello(String name){
        return "hello " + name + "welcome to spring boot world";

    }
}

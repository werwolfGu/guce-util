package com.guce.webmvc.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by chengen.gu on 2016/12/3.
 */
@Component
@ConfigurationProperties(prefix = "autor",locations={"classpath:config/autor.properties"})
public class AutorVo {

//    @Value("${autor.name}")
    private String name ;
//    @Value("${autor.age}")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }



}

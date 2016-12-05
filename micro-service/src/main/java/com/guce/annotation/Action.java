package com.guce.annotation;

import java.lang.annotation.*;

/**
 * Created by chengen.gu on 2016/12/2.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {

    String name() default "";
}

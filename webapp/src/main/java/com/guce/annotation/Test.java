package com.guce.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2016/11/27.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD})
public @interface Test {

    String value() default "";
}

package com.guce.annotation;



import java.lang.annotation.*;

/**
 * Created by Administrator on 2016/11/17.
 */
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodInfo {
    String author() default "abc";
    String date();
    String comments();
    int revision() default 1;


}

package com.guce.annotation;

/**
 * Created by Administrator on 2016/11/17.
 */
public class AnnotationExample {

    public static void main(String[] args) {

    }

    @Override
    @MethodInfo(author = "aaa",comments="commetns" ,date="2016-11017",revision = 2)
    public String toString(){
        return "overriden toString method";
    }

    @MethodInfo(comments = "oldmethod",date="2016-11-17")
    public static void oldMethod(){
        System.out.println("old method ,don't use it");
    }
}

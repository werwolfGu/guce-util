package com.guce.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/11/17.
 */
public class AnnotationParsing {

    public static void main(String[] args) {

        AnnotationExample e = new AnnotationExample();
        System.out.println(e.toString());
        try{
            ClassLoader loader = AnnotationParsing.class.getClassLoader();
            Class klass = loader.loadClass("com.guce.annotation.AnnotationExample");
            Method[] methods = klass.getMethods();
            for(Method method :methods ){
                if(method.isAnnotationPresent(com.guce.annotation.MethodInfo.class)){
                    for(Annotation anno : method.getDeclaredAnnotations()){
                        System.out.println("annotation in method " + method + " : " + anno);

                    }
                    MethodInfo methodAnno = method.getAnnotation(MethodInfo.class);
                    if(methodAnno.revision() ==1){
                        System.out.println("Method with revision no 1 = "+ method);
                    }
                }
            }

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}

package com.guce.axis2;

/**
 * Created by Administrator on 2016/6/1.
 */
public class SimpleService {
    public String getGreet(String name){
        return " hello " + name;
    }
    public int getPrice(){
        return new java.util.Random().nextInt(1000);
    }
}

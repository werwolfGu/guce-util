package com.guce;

/**
 * Created by Administrator on 2016/11/5.
 */
public class MultiThreadExample {
    private int a = 0;
    boolean flag = false;

    public void write(){
        a = 1;
        flag = true;
    }

    public void  read(){
        if(flag){
            System.out.println("a:" + a);
        }

    }
}

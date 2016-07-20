package com.guce.thread;

/**
 * Created by Administrator on 2016/5/14.
 */
public class ThreadA extends   Thread{
    private ObjectService o ;
    public ThreadA(ObjectService o){
        this.o = o;
    }
    public void run() {
        try {
            o.printT();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

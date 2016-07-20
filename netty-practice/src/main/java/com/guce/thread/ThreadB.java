package com.guce.thread;

/**
 * Created by Administrator on 2016/5/14.
 */
public class ThreadB extends   Thread{
    private ObjectService o ;
    public ThreadB(ObjectService o){
        this.o = o;
    }
    public void run() {
        try {
            o.printT2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

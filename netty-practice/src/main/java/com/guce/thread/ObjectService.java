package com.guce.thread;

/**
 * Created by Administrator on 2016/5/14.
 */
public class ObjectService {

    private  String lock1 = "lock";

    private String lock2 =  new String ("lock");

    public  void printT() throws InterruptedException {
        synchronized (lock1){
            System.out.println(Thread.currentThread().getName() + " lock1 beign time:" + System.currentTimeMillis());
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "  lock1 end time:" + System.currentTimeMillis());
        }

    }

    public  void printT2() throws InterruptedException {
        synchronized (lock1){
            System.out.println(Thread.currentThread().getName() + "  lock2 beign time:" + System.currentTimeMillis());
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " lock2 end time:" + System.currentTimeMillis());
        }

    }

}

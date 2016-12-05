package com.guce;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2016/5/4.
 */
public class Chopstick {

    private volatile Boolean enable = false;

    private final Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private String name;

    public String getName(){
        return name;
    }

    public Chopstick(){

    }
    public Chopstick(String name){
        this.name = name;
    }

    class test{
        public void print(){

        }
    }

    public void pickupChopstick(){
        String str = "123";
        check:{
            if(true){
                System.out.println(str);
                break check;
            }
        }

        lock.lock();
        try{
            if(enable){

                try {
                    condition.await();
                    enable = true   ;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                enable = true;
            }
        }finally {
            lock.unlock();
        }



    }

    public void putdownChopstick() {

        lock.lock();
        try {
             enable = false;
            condition.signal();
        }finally {
            lock.unlock();
        }

    }
}

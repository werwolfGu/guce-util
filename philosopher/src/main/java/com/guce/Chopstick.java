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

    public Chopstick(String name){
        this.name = name;
    }

    public void pickupChopstick(){

        lock.lock();
        try{
            if(enable){

                try {
                    condition.await();
                    enable = false;
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
            if (!enable) {
                enable = true;
            } else {
                enable = false;
            }
            condition.signal();
        }finally {
            lock.unlock();
        }

    }
}

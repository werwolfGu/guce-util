package com.guce;

/**
 * Created by Administrator on 2016/5/4.
 */
public class Chopstick {

    private volatile Boolean enable = false;

    private String name;
    private final String lock = "lock";

    public String getName(){
        return name;
    }

    public Chopstick(String name){
        this.name = name;
    }

    public void pickupChopstick(){

        if(enable){
            synchronized (lock){
                if(enable){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        enable = true;
    }

    public void putdownChopstick(){

        enable = false;
        synchronized (lock){
            lock.notify();
        }

    }
}

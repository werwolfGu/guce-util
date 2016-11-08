package com.guce.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/11/5.
 */
public class CustomThreadpool {

    private ThreadPoolExecutor threadPoolExecutor;

    public void init(){
        threadPoolExecutor =  new ThreadPoolExecutor(2,2,5, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(5));
    }

    class TestThread implements Runnable{

        public void run() {
            System.out.println("current Thread name:" + Thread.currentThread().getName());
        }
    }

    public void startupThread(){
        new Thread(){
            public void run(){
                for(int i = 0 ; i < 100 ; i++){
                    TestThread tt = new  TestThread();
                    try{
                        threadPoolExecutor.execute(tt);
                    }catch (Exception ex){
                        System.out.println(ex);
                    }

                }
            }
        }.start();
    }
    public static void main(String[] args) {
        CustomThreadpool t= new CustomThreadpool();
        t.init();
        t.startupThread();

    }
}

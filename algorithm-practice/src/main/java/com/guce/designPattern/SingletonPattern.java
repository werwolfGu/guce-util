package com.guce.designPattern;

/**
 * 单例模式
 * Created by Administrator on 2016/11/6.
 */
public class SingletonPattern {

    private volatile static SingletonPattern  instance ;

    private SingletonPattern(){}

    public static SingletonPattern getInstance(){

        //   1、使用双重锁加  volatile来实现单例模式
        if(instance == null){
            synchronized (SingletonPattern.class){
                if(instance == null){
                    instance = new SingletonPattern();
                }
            }
        }
        return instance;

        //    2、基于类初始化来实现单例模式  因为类初始化时 JVM会获取一个同步锁  确保同一时刻只有一个现在对该对象进行初始化
//       return SingletonPatternHelper.singletonPattern;
    }

    private static class SingletonPatternHelper{
        private static SingletonPattern singletonPattern = new SingletonPattern();
    }

    class SinglatonTestThread implements Runnable{

        public void run() {
            SingletonPattern.getInstance();
        }
    }

    public void startThread(){
        new Thread(){
            public void run(){
                for(int i = 0 ; i < 10 ; i++){
                    SinglatonTestThread tt = new SinglatonTestThread();
                    Thread t = new Thread(tt);
                    t.setName("singleton i-->" + i);
                    t.start();
                }
            }
        }.start();

    }

    public static void main(String[] args) {
        SingletonPattern s = new SingletonPattern();
        s.startThread();
    }
}

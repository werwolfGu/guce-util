package com.guce.thread;

/**
 * Created by Administrator on 2016/5/14.
 */
public class MainService {
    public static void main(String[] args) {

        ObjectService o1 = new ObjectService();
        ObjectService o2 = new ObjectService();
        ThreadA a = new ThreadA(o1);
        ThreadB b = new ThreadB(o1);
        a.start();
        b.start();
    }
}

package com.guce;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Hello world!
 *
 */
public class App 
{

    private final AtomicInteger ctl = new AtomicInteger(0);
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private final static Map<String,String> map = new HashMap();


    public static void main( String[] args ){
       /* BlockingQueue<String> queue = new LinkedBlockingQueue();
        try {
            Object obj = queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        ConcurrentMap map = new ConcurrentHashMap();
        map.put("key","dsd");
        System.out.println( "Hello World!" );
        System.out.println("map size:" + map.size());
        map.put("key","value");
        map.put("key1","value");
        System.out.println("map size:" + map.size());
        String d = "hfdnfjdshuhj";
        d.hashCode();
        Object o = new Object();
        o.hashCode();
        int x1 = 8&43233;
        int x2 = 12332&43233;
        System.out.println("8&10 :" + x1 + " 1233787879892&10 :" + x2 );
        map.put("abc","dddd");

        System.out.println("Integer.SIZE " + Integer.MAX_VALUE);
        System.out.println("Integer.SIZE " + COUNT_BITS);
        System.out.println("CAPACITY " + CAPACITY);

        System.out.println("App.runStateOf(0)  " + App.runStateOf(0));
        System.out.println("App.workerCountOf(0)  " + App.workerCountOf(0));
        App a = new App();
        System.out.println(a.ctl.get());
        System.out.println(a.ctl.get());
    }
}

package com.guce;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ConcurrentMap map = new ConcurrentHashMap();
        BlockingQueue b = new LinkedBlockingQueue();
        try {
            b.put("asss");
            b.poll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put("a","a");
        System.out.println( "Hello World!" );
    }
}

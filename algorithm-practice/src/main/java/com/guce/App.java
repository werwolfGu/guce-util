package com.guce;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
        BlockingQueue<String> queue = new LinkedBlockingQueue();
        try {
            Object obj = queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println( "Hello World!" );
    }
}

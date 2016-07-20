package com.guce;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Hello world!
 *
 */
public class App {
    public boolean flag ;
    BlockingQueue<String> b = new LinkedBlockingQueue<String>();

    public void queueCheck(){
        try {
            String s = b.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main( String[] args )
    {
        App a = new App();
        System.out.println(a.flag);
        System.out.println( "Hello World!" );

        String st = new String("string");
        String st1 = new String("string");
        System.out.println(st.hashCode() + "  ;" + (st.hashCode()==st1.hashCode()));
    }
}

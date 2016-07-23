package com.guce.ccb;

import com.guce.thread.ThreadPoolUtil.ThreadPoolService;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/7/23.
 */
public class BootStrap {
    public static void main(String[] args) {

        FetchCCbNoblemetalData fetchCCbNoblemetalData = new FetchCCbNoblemetalData();

        ThreadPoolService.getSchedulePool().scheduleAtFixedRate(fetchCCbNoblemetalData,1,3, TimeUnit.SECONDS);
       /* BlockingQueue b = new LinkedBlockingQueue();
        try {
            b.poll(5000,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}

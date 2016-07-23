package com.guce.thread.ThreadPoolUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by Administrator on 2016/7/23.
 */
public class ThreadPoolService {

    private static ScheduledExecutorService schedulePool = Executors.newScheduledThreadPool(10);
    private static ExecutorService fixFhreadPool = Executors.newFixedThreadPool(3);
    private static  ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();

    public static ScheduledExecutorService getSchedulePool() {
        return schedulePool;
    }

    public static void setSchedulePool(ScheduledExecutorService schedulePool) {
        ThreadPoolService.schedulePool = schedulePool;
    }

    public static ExecutorService getFixFhreadPool() {
        return fixFhreadPool;
    }

    public static void setFixFhreadPool(ExecutorService fixFhreadPool) {
        ThreadPoolService.fixFhreadPool = fixFhreadPool;
    }

    public static ExecutorService getSingleThreadPool() {
        return singleThreadPool;
    }

    public static void setSingleThreadPool(ExecutorService singleThreadPool) {
        ThreadPoolService.singleThreadPool = singleThreadPool;
    }
}

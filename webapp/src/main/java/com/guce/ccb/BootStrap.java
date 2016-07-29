package com.guce.ccb;

import com.guce.thread.ThreadPoolUtil.ThreadPoolService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/7/23.
 */
public class BootStrap implements InitializingBean,DisposableBean,FactoryBean {

    public void afterPropertiesSet() throws Exception {

        FetchCCbNoblemetalData fetchCCbNoblemetalData = new FetchCCbNoblemetalData();

        ThreadPoolService.getSchedulePool().scheduleAtFixedRate(fetchCCbNoblemetalData,1,3, TimeUnit.SECONDS);
    }

    public void destroy() throws Exception {

    }

    public Object getObject() throws Exception {
        return null;
    }

    public Class<?> getObjectType() {
        return null;
    }

    public boolean isSingleton() {
        return false;
    }

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

package com.guce.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/9/20.
 */

@Component("springBeanImplOrder")
public class SpringBeanImplOrder  implements InitializingBean, DisposableBean, ApplicationContextAware, ApplicationListener, BeanNameAware {


    public void setBeanName(String s) {

    }

    public void destroy() throws Exception {

        System.out.println("disposableBean implement");
    }

    public void afterPropertiesSet() throws Exception {

        System.out.println("initializingBean impl");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        System.out.println("ApplicationContextAware implement");
    }

    public void onApplicationEvent(ApplicationEvent applicationEvent) {

        System.out.println("ApplicationListener implement");
    }
}

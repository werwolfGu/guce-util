package com.guce.designPattern.proxy.impl;

import com.guce.designPattern.proxy.IProxy;
import com.sun.javafx.binding.StringFormatter;

/**
 * Created by Administrator on 2016/11/8.
 */
public class ProxySubject implements IProxy {

    private IProxy realSubject;
    public ProxySubject(){
        realSubject = new RealSubject();   //关系在编译时确定
    }

    public ProxySubject(IProxy subject){
        this.realSubject = subject;

    }
    public void request(String name) {
        System.out.println(String.format("代理模式或装饰模式！%s",name));
        realSubject.request(name);
    }
}

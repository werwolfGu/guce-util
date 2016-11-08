package com.guce.designPattern.proxy;

import com.guce.designPattern.proxy.impl.ProxySubject;
import com.guce.designPattern.proxy.impl.RealSubject;

/**
 * Created by Administrator on 2016/11/8.
 */
public class Testmain {

    public static void main(String[] args) {
        IProxy proxy = new ProxySubject();
        proxy.request("   代理模式");

        IProxy proxy1 = new RealSubject();
        proxy = new ProxySubject(proxy1);
        proxy.request("  装饰器模式");
    }
}

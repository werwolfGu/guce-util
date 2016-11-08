package com.guce.designPattern.proxy.impl;

import com.guce.designPattern.proxy.IProxy;

/**
 * Created by Administrator on 2016/11/8.
 */
public class RealSubject implements IProxy {

    public void request(String name) {
        System.out.println("具体实现类操作！");
    }
}

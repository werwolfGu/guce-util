package com.guce.designPattern.ObserverPattern.impl;

import com.guce.designPattern.ObserverPattern.Observer;
import com.guce.designPattern.ObserverPattern.Subject;

/**
 * Created by Administrator on 2016/5/19.
 */
public class HaiGui implements Observer {
    Subject subject;

    public HaiGui(Subject subject){
        this.subject = subject;
        subject.addObserver(this);
    }
    public void hearTelephone(String hearMess) {
        System.out.println("海龟观察者：" + hearMess);
    }
}

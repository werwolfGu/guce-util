package com.guce.designPattern.ObserverPattern.impl;

import com.guce.designPattern.ObserverPattern.Observer;
import com.guce.designPattern.ObserverPattern.Subject;

/**
 * Created by Administrator on 2016/5/19.
 */
public class UniversitySutdent implements Observer {
    Subject subject;

    public UniversitySutdent(Subject subject){
        this.subject = subject;
        this.subject.addObserver(this);
    }
    public void hearTelephone(String hearMess) {
        System.out.println("university student :" + hearMess);
    }
}

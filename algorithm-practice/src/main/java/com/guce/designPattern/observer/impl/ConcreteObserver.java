package com.guce.designPattern.observer.impl;

import com.guce.designPattern.observer.ObserverInterface;
import com.guce.designPattern.observer.SubjectInterface;

/**
 * Created by Administrator on 2016/11/7.
 */
public class ConcreteObserver implements ObserverInterface {

    private SubjectInterface subject;
    private String name;

    public ConcreteObserver(SubjectInterface subject,String name){

        this.name = name;
        this.subject = subject;
        this.subject.addObserver(this);
    }
    public void update(String msg) {
        System.out.println("通知到具体观察者:" + name + " ; 推数据：" + msg);

        if(subject instanceof ConcreteSubject){
            System.out.println("拉数据：" + ((ConcreteSubject)subject).getPrice());
        }
    }
}

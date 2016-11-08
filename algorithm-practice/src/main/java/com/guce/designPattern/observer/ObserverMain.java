package com.guce.designPattern.observer;

import com.guce.designPattern.observer.impl.ConcreteObserver;
import com.guce.designPattern.observer.impl.ConcreteObserverTwo;
import com.guce.designPattern.observer.impl.ConcreteSubject;

/**
 * Created by Administrator on 2016/11/7.
 */
public class ObserverMain {
    public static void main(String[] args) {
        //具体主题
        ConcreteSubject subject = new ConcreteSubject();

        //具体观察者
        ObserverInterface observer1 = new ConcreteObserver(subject,"observer1");
        ObserverInterface observer2 = new ConcreteObserverTwo(subject,"observer2");
        subject.setName("VIpshop");
        subject.setPrice("123456");
        subject.notifyObserver();
    }
}

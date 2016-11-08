package com.guce.designPattern.observer.impl;

import com.guce.designPattern.observer.ObserverInterface;
import com.guce.designPattern.observer.SubjectInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/7.
 */
public class ConcreteSubject implements SubjectInterface {

    String name;
    String price;
    private List<ObserverInterface> list = new ArrayList<ObserverInterface>();

    public void addObserver(ObserverInterface observer) {
        list.add(observer);
    }

    public void deleteObserver() {

    }

    public void notifyObserver() {
        for(ObserverInterface observer:list){
            observer.update(name);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

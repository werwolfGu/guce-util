package com.guce.designPattern.ObserverPattern.impl;

import com.guce.designPattern.ObserverPattern.Observer;
import com.guce.designPattern.ObserverPattern.Subject;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/5/19.
 */
public class SeekJobSubject implements Subject {
    private volatile boolean changed = false;
    private String mess;
    private Set<Observer> observers = new HashSet<Observer>();

    public void setMess(String mess){
        if(mess != null && !mess.equals(this.mess)){
            this.mess = mess;
            changed = true;
        }
    }
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void deleteObserver(Observer observer) {
        if(observers.contains(observer)){
            observers.remove(observer);
        }
    }

    public void notifyObservers() {
        if(changed){
            for(Observer observer : observers){
                observer.hearTelephone(mess);
            }
            changed = false;
        }

    }
}

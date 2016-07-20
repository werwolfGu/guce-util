package com.guce.designPattern.ObserverPattern;

/**
 * Created by Administrator on 2016/5/19.
 */
public interface Subject {
    public void addObserver(Observer observer);
    public void deleteObserver(Observer observer);
    public void notifyObservers();

}

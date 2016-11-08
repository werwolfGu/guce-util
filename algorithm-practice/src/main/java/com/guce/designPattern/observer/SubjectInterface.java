package com.guce.designPattern.observer;

/**
 * Created by Administrator on 2016/11/7.
 */
public interface SubjectInterface {

    public void addObserver(ObserverInterface observer);
    public void deleteObserver();
    public void  notifyObserver();

}

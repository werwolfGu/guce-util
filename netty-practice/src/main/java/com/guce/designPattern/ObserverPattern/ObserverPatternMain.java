package com.guce.designPattern.ObserverPattern;

import com.guce.designPattern.ObserverPattern.impl.HaiGui;
import com.guce.designPattern.ObserverPattern.impl.SeekJobSubject;
import com.guce.designPattern.ObserverPattern.impl.UniversitySutdent;

/**
 * Created by Administrator on 2016/5/19.
 */
public class ObserverPatternMain {

    public static void main(String[] args) {
        SeekJobSubject subject = new SeekJobSubject();
        Observer student = new UniversitySutdent(subject);
        subject.setMess("大学生 招聘信息！");
        subject.notifyObservers();
        Observer haigui = new HaiGui(subject);
        subject.setMess("海龟招聘信息！");
        subject.notifyObservers();
        subject.setMess("海龟招聘信息！");
        subject.notifyObservers();
    }
}

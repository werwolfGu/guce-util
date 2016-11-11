package com.guce.designPattern.decorator.impl;

import com.guce.designPattern.decorator.Brid;

/**
 * Created by Administrator on 2016/11/8.
 */
public abstract class Decoator implements Brid{
    protected Brid bird;

    public Decoator(){

    }
    public Decoator(Brid bird){
        this.bird = bird;
    }
    public abstract int fly() ;
}

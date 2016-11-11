package com.guce.designPattern.decorator.impl;

import com.guce.designPattern.decorator.Brid;

/**
 * Created by Administrator on 2016/11/8.
 */
public class SparrowDecorator extends Decoator {
    private final int DISTANCE = 50;

    public SparrowDecorator(Brid bird){
        super(bird);
    }
    public int fly() {
        return bird.fly() + eleFly();
    }

    private int eleFly(){
        return DISTANCE;
    }

}

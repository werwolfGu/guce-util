package com.guce.designPattern.decorator.impl;

import com.guce.designPattern.decorator.Brid;

/**
 * Created by Administrator on 2016/11/8.
 */
public class Sparrow implements Brid {
    private final int distance = 100;
    public int fly() {
        return distance;
    }
}

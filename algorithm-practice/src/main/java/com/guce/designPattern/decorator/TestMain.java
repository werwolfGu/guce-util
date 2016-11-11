package com.guce.designPattern.decorator;

import com.guce.designPattern.decorator.impl.Decoator;
import com.guce.designPattern.decorator.impl.Sparrow;
import com.guce.designPattern.decorator.impl.SparrowDecorator;

/**
 * Created by Administrator on 2016/11/8.
 */
public class TestMain {
    public static void main(String[] args) {
        Brid brid = new Sparrow();
        System.out.println(brid.fly());
        Decoator d = new SparrowDecorator(brid);
        System.out.println(d.fly());
    }
}

package com.guce.designPattern.commandPattern;

/**
 * Created by Administrator on 2016/5/18.
 */
public class PrintLetter {
    public void printEnglish(){
        for(char c = 'a' ; c <='z' ; c++){
            System.out.print(" " + c);
        }
    }
}

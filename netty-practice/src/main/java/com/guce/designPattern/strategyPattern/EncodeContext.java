package com.guce.designPattern.strategyPattern;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Administrator on 2016/5/23.
 */
public class EncodeContext {
    EncryptStrategy encryptStrategy;
    public void setEncryptStrategy(EncryptStrategy encryptStrategy){
        this.encryptStrategy = encryptStrategy;
    }

    public void encryptFile(File file){
        try {
            encryptStrategy.encryptFile(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String decryptFile(File file){
        String context = null;
        try {
           context = encryptStrategy.decryptFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return context;
    }

    public static void main(String[] args) {

    }
}

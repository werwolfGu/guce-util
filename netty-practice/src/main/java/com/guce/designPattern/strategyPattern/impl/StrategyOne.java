package com.guce.designPattern.strategyPattern.impl;

import com.guce.designPattern.strategyPattern.EncryptStrategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2016/5/23.
 */
public class StrategyOne implements EncryptStrategy {
    private String password;

    public StrategyOne(){
        password = "I love this game";

    }
    public StrategyOne(String password){
        if(password == null || "".equals(password)){
            password = "I love this game";
        }
        this.password = password;
    }

    public void encryptFile(File file){
        byte[] bytes = password.getBytes();
        try {
            FileInputStream in = new FileInputStream(file);
            long length = file.length();
            byte[] c = new byte[(int)length];
            int m = in.read(c);
            for( int k = 0 ; k < m ; k++){
                int n = c[k] + bytes[k%bytes.length];
                c[k] = (byte)n;
            }
            in.close();
            FileOutputStream out = new FileOutputStream(file);
            out.write(c,0,m);
            out.close();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }

    public String decryptFile(File file) {

        byte[] bytes = password.getBytes();
        long length = file.length();
        try {
            FileInputStream in = new FileInputStream(file);
            byte[] c = new byte[(int)file.length()];

            int m = in.read(c);
            for(int k = 0 ; k < m ; k++){
                int n = c[k] - bytes[k%bytes.length];
                c[k] = (byte) n;
            }

            in.close();
            return new String(c,0,m);
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}

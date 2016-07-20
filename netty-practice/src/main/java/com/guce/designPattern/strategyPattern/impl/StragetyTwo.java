package com.guce.designPattern.strategyPattern.impl;

import com.guce.designPattern.strategyPattern.EncryptStrategy;

import java.io.*;

/**
 * Created by Administrator on 2016/5/23.
 */
public class StragetyTwo implements EncryptStrategy {

    private String password;
    public StragetyTwo(){
        password = "I love this game";
    }
    public StragetyTwo(String password){
        if(password == null || "".equals(password)){
            password = "I love this game";
        }
        this.password = password;
    }
    public void encryptFile(File file)  {
        byte[] bytes = password.getBytes();
        try {
            FileInputStream in = new FileInputStream(file);
            long length = file.length();
            byte[] c = new byte[(int)length];
            int m = in.read(c);
            for(int k = 0 ; k < m ; k++){
                int n = c[k]^bytes[k%bytes.length];
                c[k] = (byte)n;
            }
            in.close();
            FileOutputStream out = new FileOutputStream(file);
            out.write(c,0,m);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String decryptFile(File file)  {
        byte[] bytes = password.getBytes();
        try {
            FileInputStream in = new FileInputStream(file);
            long length = file.length();
            byte[] c = new byte[(int)length];
            int m = in.read(c);
            for(int k = 0 ; k < m ; k++){
                int n = c[k]^bytes[k%bytes.length];
                c[k] = (byte)n;
            }
            in.close();
            return new String(c,0,m);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

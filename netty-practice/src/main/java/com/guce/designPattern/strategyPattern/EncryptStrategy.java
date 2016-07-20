package com.guce.designPattern.strategyPattern;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Administrator on 2016/5/23.
 */
public interface EncryptStrategy {

    public void encryptFile(File file) throws FileNotFoundException;
    public String decryptFile(File file) throws IOException;
}

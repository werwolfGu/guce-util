package com.guce.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/7/20.
 */
public class ImagesTools {

    public static int getImagesWidth(File file){
        InputStream is = null;
        BufferedImage src = null;
        int ret = -1;

        try{
            is = new FileInputStream(file);
            src = ImageIO.read(is);
            ret = src.getWidth();
        }catch(Exception ex){

        }

        return ret;
    }

    public static int getImageHeight(File file){
        InputStream is = null;
        BufferedImage src = null;
        int ret = -1;

        try{
            is = new FileInputStream(file);
            src = ImageIO.read(is);
            ret = src.getHeight();
        }catch(Exception ex){

        }

        return ret;
    }

    public static void main(String[] args) {
        File file = new File("E:\\picture\\shanghai\\顾呈恩.jpg");
        int width = ImagesTools.getImagesWidth(file);
        int height = ImagesTools.getImageHeight(file);
        System.out.println("width:" + width + " height:" + height);
    }
}

package com.guce.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

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
    class ThreadTest extends Thread{

        public void run() {
            while(true){
                System.out.println(new Date());
                try {
                    Thread.sleep(1000*60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void initThread(){
        ThreadTest t = new ThreadTest();
        t.start();
    }


    public static void main(String[] args) {
        File file = new File("E:\\picture\\shanghai\\顾呈恩.jpg");
        int width = ImagesTools.getImagesWidth(file);
        int height = ImagesTools.getImageHeight(file);
        System.out.println("width:" + width + " height:" + height);
        ImagesTools i = new ImagesTools();
        i.initThread();


    }
}

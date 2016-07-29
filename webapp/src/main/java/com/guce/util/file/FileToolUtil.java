package com.guce.util.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/7/23.
 */
public class FileToolUtil {

    private static RandomAccessFile randomAccessFile = null;
    private static String  ACCESS_MODE = "rw";
    private static Object lock = new Object();
    private static FileChannel fc = null;
    private static FileLock fileLock = null;
    private static String BASE_FILE_PATH = "D:\\CCB\\GOLD_PRICE_DETAIL";

    //记录拉取 的数据的数目
    private static int DATA_NUM = 0;

    public static void init(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String filename = sdf.format(new Date()) + ".txt";

        init(BASE_FILE_PATH,filename);
    }


    public static void init(String filePath,String filename){
        try {
            File file = new File(filePath,filename);
            if(!file.exists()){
                synchronized (lock){
                   CHECK_FILE:{
                       //目录都不存在
                       file = new File(filePath);
                       if(!file.exists()){
                           file.mkdirs();
                       }
                       //目录存在   但文件不存在
                       file = new File(filePath,filename);
                       if(!file.exists()){
                           file.createNewFile();
                           DATA_NUM = 0;
                           randomAccessFile = new RandomAccessFile(file,ACCESS_MODE);
                           fc = randomAccessFile.getChannel();
                           fc.position(0);
                           randomAccessFile.writeInt(DATA_NUM);
                           break CHECK_FILE;
                       }
                   }

                }
            }
            if(randomAccessFile == null){

                synchronized (lock){
                    if(randomAccessFile == null){
                        randomAccessFile = new RandomAccessFile(file,ACCESS_MODE);
                        fc = randomAccessFile.getChannel();

                    }
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(String content){
        init();
        try {
            long position = fc.position();
            fileLock = fc.tryLock();
            try {
                long size = randomAccessFile.length();
                if(position < size ){
                    position = size;
                }
                fc.position(position);
                int length = content.getBytes().length;
                randomAccessFile.writeLong(length);
                byte[] bytes = content.getBytes("UTF-8");
                randomAccessFile.write(bytes,0,bytes.length);

                fc.position(0);
                int num = randomAccessFile.readInt();
                num++;
                fc.position(0);
                randomAccessFile.writeInt(num);

            }finally {
                fileLock.release();
            }



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String readFile(){
        init();
        try {
            int num = randomAccessFile.readInt();
            System.out.println("num:" + num);

           for(int i = 0 ; i < num ; i++){
                long length = randomAccessFile.readLong();
                byte[] b = new byte[(int) length];

                randomAccessFile.read(b,0,b.length);
                System.out.println(new String(b,"UTF-8"));
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getCCBDataNum(){
        init();
        int num = 0;
        try {
             num = randomAccessFile.readInt();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return num;
    }

    public static void main(String[] args) {
        /*for(int i = 0 ; i < 1000 ; i++){
            writeFile("coneteneddjafjkadfjlsfjlsafjkdsafkdsajfdsakjfskjsf");
        }*/
        getCCBDataNum();
//        readFile();
      /*  String s = "coneteneddjafjkadfjlsfjlsafjkdsafkdsajfdsakjfskjsf";
        System.out.println(s.length());*/
    }
}

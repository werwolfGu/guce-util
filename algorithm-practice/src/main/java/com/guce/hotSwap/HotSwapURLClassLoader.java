package com.guce.hotSwap;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * 参考：http://www.blogjava.net/heavensay/archive/2012/11/07/389685.html
 * Created by Administrator on 2016/5/9.
 */
public class HotSwapURLClassLoader extends URLClassLoader {

    //缓存加载class文件的最后最新修改时间
    public static Map<String,Long> cacheLastModifyTimeMap = new HashMap<String,Long>();
    //工程class所在的路径
    public static String projectClassPath = "D:/Ecpworkspace/ZJob-Note/bin/";
    ////所有的测试的类都在同一个包下
    public static String packagePath = "testjvm/testclassloader/";

    private static HotSwapURLClassLoader hcl = new HotSwapURLClassLoader();
    public HotSwapURLClassLoader(){
        super(getMyUrls());
    }

    private static URL[] getMyUrls(){
        URL  url = null;
        try {
            url = new File(projectClassPath).toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return new URL[]{url};
    }

    public HotSwapURLClassLoader(URL[] urls) {
        super(urls);
    }

    public Class<?> loadClass(String name,boolean resolve){
        Class clazz = null;
        clazz = findLoadedClass(name);
        if(clazz != null){
            if(resolve){
                resolveClass(clazz);
            }
        }
        return null;
    }
}

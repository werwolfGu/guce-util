package com.guce.aware;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.getFile;

/**
 *BeanNameAware 获得到bean容器的名称
 * ResourceLoaderAware获得资源加载器  可以获取外部资源文件
 * Created by chengen.gu on 2016/12/3.
 */
@Service
public class AwareServiceDemo implements BeanNameAware,ResourceLoaderAware {

    private String beanName;
    private ResourceLoader resourceLoader;
    private static Logger logger = LoggerFactory.getLogger(AwareServiceDemo.class);
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void outputResult(){
        logger.info("beanname:{}",this.beanName);
        Resource resource = resourceLoader.getResource("classpath:com/guce/aware/resource.txt");
        try {

            logger.info(resource.getFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

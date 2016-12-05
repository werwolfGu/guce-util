package com.guce;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by chengen.gu on 2016/12/2.
 */
@Configuration
@ComponentScan("com.guce")
@EnableAspectJAutoProxy     //开启对aspect的支持
@EnableAsync                //开启对异步方法的支持
@EnableScheduling           //开启对任务的执行
@EnableWebMvc               //开启对webmvc的支持
@EnableCaching              //开启对注解缓存的支持
@EnableConfigurationProperties  //开启对 COnfigurationProperties 注解配置的bean支持
public class GuConfig {

}

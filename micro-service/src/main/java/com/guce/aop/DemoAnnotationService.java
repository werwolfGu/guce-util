package com.guce.aop;

import com.guce.annotation.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by chengen.gu on 2016/12/2.
 */
@Service("demoAnnotationService")
public class DemoAnnotationService {

    private static Logger logger = LoggerFactory.getLogger(DemoAnnotationService.class);

    @Action(name="注解拦截的 add 操作")
    public void add(){
        logger.info("Action  注解 add");
    }
}

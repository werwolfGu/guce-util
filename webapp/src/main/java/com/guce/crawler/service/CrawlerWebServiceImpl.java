package com.guce.crawler.service;

import com.guce.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by chengen.gu on 2016/12/1.
 */
@Service("craslerWebService")
public class CrawlerWebServiceImpl implements ICrawlerWebService {

    private static Logger logger = LoggerFactory.getLogger(CrawlerWebServiceImpl.class);


    public String climbToSinaWebData(Map<String, String> requestMap) throws ServiceException {
        return null;
    }
}

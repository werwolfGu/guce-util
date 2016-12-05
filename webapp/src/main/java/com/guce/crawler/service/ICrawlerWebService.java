package com.guce.crawler.service;

import com.guce.exception.ServiceException;

import java.util.Map;

/**
 * Created by chengen.gu on 2016/12/1.
 */
public interface ICrawlerWebService {

    public String climbToSinaWebData(Map<String,String> requestMap) throws ServiceException;
}

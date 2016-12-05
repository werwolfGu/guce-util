package com.guce.exception;

/**
 * Created by chengen.gu on 2016/12/1.
 */
public class ServiceException extends RuntimeException {

    public ServiceException(){
        super();
    }

    public ServiceException(String msg){
        super(msg);
    }

    public ServiceException(String msg,Throwable cause){
        super(msg,cause);

    }

}

package com.guce.exception;

/**
 * Created by chengen.gu on 2016/12/1.
 */
public class DaoException extends RuntimeException {

    public DaoException(){
        super();
    }

    public DaoException(String msg){
        super(msg);
    }

    public DaoException(String msg,Throwable cause){
        super(msg,cause);
    }

}

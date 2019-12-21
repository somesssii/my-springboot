package com.stnb.myspringboot.error;

/**
 * 业务异常
 * @author 11299
 */
public class BusinessException extends RuntimeException{

    public BusinessException() {};

    public BusinessException(String message) {
        super(message);
    }
}

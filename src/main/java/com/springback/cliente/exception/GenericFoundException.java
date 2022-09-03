package com.springback.cliente.exception;

import com.springback.cliente.config.ErrorCode;

public class GenericFoundException extends GenericException {

    private static final long serialVersion = 1L;

    public GenericFoundException(ErrorCode errorCode, String message){
        super(errorCode,message);
    }
}

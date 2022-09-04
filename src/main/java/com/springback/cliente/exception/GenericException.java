package com.springback.cliente.exception;

import com.springback.cliente.config.ErrorCode;

public class GenericException extends RuntimeException{

    private static final long serialVersionUID =1L;

    private static final String SPACE = " ";
    private static final String COMMA = ",";
    private final ErrorCode errorCode;

    public GenericException(ErrorCode errorCode) {
        super(errorCode.getReasonPhrase());
        this.errorCode = errorCode;
    }

    public GenericException(ErrorCode errorCode,String message){
        super(BuildMessage(message,errorCode));
        this.errorCode =errorCode;
    }

    private static String BuildMessage(String message, ErrorCode errorCode) {
        return errorCode.getReasonPhrase() + COMMA + SPACE + message;
    }


}

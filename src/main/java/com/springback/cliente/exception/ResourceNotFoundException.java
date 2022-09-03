package com.springback.cliente.exception;


import com.springback.cliente.config.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends GenericException{

    private static final long serialVersion = 1L;

    public ResourceNotFoundException(ErrorCode errorCode, String message){
        super(errorCode,message);
    }
}

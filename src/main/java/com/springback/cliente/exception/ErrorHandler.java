package com.springback.cliente.exception;

import brave.Tracer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.springback.cliente.config.ErrorCode;
import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Optional;

@ControllerAdvice
@Slf4j
public class ErrorHandler {
    private final Tracer tracer;

    public ErrorHandler(Tracer tracer) {
        this.tracer = tracer;
    }

    @ExceptionHandler({
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMediaTypeNotAcceptableException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestPartException.class,
            BindException.class,
            NoHandlerFoundException.class,
            AsyncRequestTimeoutException.class
    })
    public final ResponseEntity<ApiErrorResponse> handleException(Exception ex, HttpServletRequest request)  {
        String path = request.getRequestURI();
        log.error("Error Bad Request: ", ex);
        return buildResponseError(HttpStatus.BAD_REQUEST, ex, ErrorCode.ERROR_INPUT, path);
    }

    // Exception Generica cuando el Error no está controlado
    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiErrorResponse> handle(Exception ex, HttpServletRequest request) {
        String path = request.getRequestURI();
        log.error("Error Bad Request: ", ex);
        return buildResponseError(HttpStatus.INTERNAL_SERVER_ERROR, ex, ErrorCode.ERROR_NO_CONTROLADO, path);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ApiErrorResponse> handle(ResourceNotFoundException ex, HttpServletRequest request) {
        String path = request.getRequestURI();
        log.error("Error Bad Request: ", ex);
        return buildResponseError(HttpStatus.NOT_FOUND, ex, ErrorCode.ENTIDAD_NO_ENCONTRADA, path);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ApiErrorResponse> handle(ConstraintViolationException ex, HttpServletRequest request) {
        String path = request.getRequestURI();
        log.error("Error Bad Request: ", ex);
        return buildResponseError(HttpStatus.BAD_REQUEST, ex, ErrorCode.ERROR_INPUT, path);
    }


    @ExceptionHandler(CannotCreateTransactionException.class)
    ResponseEntity<ApiErrorResponse> handle(CannotCreateTransactionException ex, HttpServletRequest request) {
        String path = request.getRequestURI();
        log.error("Error Bad Request: ", ex);
        return buildResponseError(HttpStatus.INTERNAL_SERVER_ERROR, ex, ErrorCode.ERROR_CONEXION, path);
    }
    private ResponseEntity<ApiErrorResponse> buildResponseError(HttpStatus httpStatus, Throwable ex, ErrorCode errorCode, String path) {

        var traceId = Optional.ofNullable(this.tracer.currentSpan())
                .map(span -> span.context().traceIdString())
                .orElse("No existe traza");

        ApiErrorResponse apiErrorResponse = ApiErrorResponse
                .builder()
                .timestamp(LocalDateTime.now())
                .status(httpStatus.getReasonPhrase())
                .statusCode(httpStatus.value())
                .code(errorCode.value())
                .message(ex.getMessage())
                .type(ex.getClass().getCanonicalName())
                .xB3traceId(traceId)
                .path(path)
                .build();

        return new ResponseEntity<>(apiErrorResponse, httpStatus);
    }


    @Builder
    @NonNull
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @ToString
    private static class ApiErrorResponse {
        @JsonProperty
        private String status;
        @JsonProperty
        private Integer statusCode;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        private LocalDateTime timestamp;
        @JsonProperty
        private String message;
        @JsonProperty
        private Integer code;
        @JsonProperty
        private String type;
        @JsonProperty
        private String xB3traceId;
        @JsonProperty
        private String path;
    }

}

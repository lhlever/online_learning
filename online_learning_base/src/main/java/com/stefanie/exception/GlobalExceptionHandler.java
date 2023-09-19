package com.stefanie.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @Author：stefanie
 * @Package：com.stefanie.exception
 * @Project：online_learning
 * @name：GlobalExceptionHandler
 * @Date：2023/9/19 19:42
 * @Filename：GlobalExceptionHandler
 * @Description：
 */
@ControllerAdvice
@Slf4j
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(GlobalException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse customException(GlobalException e){

        log.error("系统异常{}",e.getMessage(),e);
        String message = e.getMessage();
        RestErrorResponse restErrorResponse = new RestErrorResponse(message);
        return restErrorResponse;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse exception(Exception e){
        log.error("系统异常{}",e.getMessage(),e);
        RestErrorResponse restErrorResponse = new RestErrorResponse(CommonError.UNKOWN_ERROR.getErrMessage());
        return restErrorResponse;
    }
}

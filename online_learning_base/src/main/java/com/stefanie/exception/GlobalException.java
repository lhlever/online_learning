package com.stefanie.exception;

/**
 * @Author：stefanie
 * @Package：com.stefanie.exception
 * @Project：online_learning
 * @name：GlobalException
 * @Date：2023/9/19 18:34
 * @Filename：GlobalException
 * @Description：
 */
public class GlobalException extends RuntimeException{
    private String errMessage;
    public GlobalException(String errMessage){
        super(errMessage);
        this.errMessage=errMessage;
    }

    public static void cast(String errMessage){
        throw new GlobalException(errMessage);
    }

    public static void cast(CommonError commonError){
        throw new GlobalException(commonError.getErrMessage());
    }
}

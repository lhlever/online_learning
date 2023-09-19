package com.stefanie.exception;

/**
 * @Author：stefanie
 * @Package：com.stefanie.exception
 * @Project：online_learning
 * @name：CommonError
 * @Date：2023/9/19 18:37
 * @Filename：CommonError
 * @Description：
 */
public enum CommonError {

    UNKOWN_ERROR("执行过程异常，请重试。"),
    PARAMS_ERROR("非法参数"),
    OBJECT_NULL("对象为空"),
    QUERY_NULL("查询结果为空"),
    REQUEST_NULL("请求参数为空");

    private String errMessage;

    public String getErrMessage() {
        return errMessage;
    }

    private CommonError( String errMessage) {
        this.errMessage = errMessage;
    }

}

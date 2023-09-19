package com.stefanie.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.io.Serializable;

/**
 * @Author：stefanie
 * @Package：com.stefanie.exception
 * @Project：online_learning
 * @name：RestErrorResponse
 * @Date：2023/9/19 18:30
 * @Filename：RestErrorResponse
 * @Description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestErrorResponse implements Serializable {
    private String errMessage;
}

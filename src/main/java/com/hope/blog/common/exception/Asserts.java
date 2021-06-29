package com.hope.blog.common.exception;

import com.hope.blog.common.api.IErrorCode;

/**
 * 断言类，用于抛出异常
 * Create by lijin on 2021/3/20 18:29
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}

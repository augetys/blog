package com.hope.blog.common.exception;

import com.hope.blog.common.api.CommonResult;
import com.hope.blog.utils.ThrowableUtil;
import com.qiniu.common.QiniuException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * Create by lijin on 2021/3/20 18:16
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义异常
     *
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public <T> CommonResult<T> handle(BusinessException e) {
        log.error(ThrowableUtil.getStackTrace(e));
        log.info("-----------------------------");
        log.error("Exception", e);
        if (e.getErrorCode() != null) {
            return CommonResult.failed(e.getErrorCode());
        }
        return CommonResult.failed(e.getMessage());
    }

    /**
     * 七牛云异常
     *
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler(value = QiniuException.class)
    public <T> CommonResult<T> handle(QiniuException e) {
        log.error("七牛云异常:{}", e.response);
        return CommonResult.failed();
    }


    /**
     * 未定义异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = Exception.class)
    public <T> CommonResult<T> handleException(Exception e) {
        log.error("未定义异常:", e);
        return CommonResult.failed();
    }
}

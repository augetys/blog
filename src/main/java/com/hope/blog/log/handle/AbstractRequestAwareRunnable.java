package com.hope.blog.log.handle;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Created by lijin on  2021/6/22
 */
public abstract class AbstractRequestAwareRunnable implements Runnable {

    private final RequestAttributes requestAttributes;
    private Thread thread;

    public AbstractRequestAwareRunnable() {
        this.requestAttributes = RequestContextHolder.getRequestAttributes();
        this.thread = Thread.currentThread();
    }

    @Override
    public void run() {
        try {
            RequestContextHolder.setRequestAttributes(requestAttributes);
            onRun();
        } finally {
            if (Thread.currentThread() != thread) {
                RequestContextHolder.resetRequestAttributes();
            }
            thread = null;
        }
    }

    /**
     * 启动
     */
    protected abstract void onRun();
}

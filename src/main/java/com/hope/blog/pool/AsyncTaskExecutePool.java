package com.hope.blog.pool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步任务线程池装配类
 *
 * @author https://juejin.im/entry/5abb8f6951882555677e9da2
 * https://blog.csdn.net/wszhlzjl/article/details/104422288
 * @date 2019年10月31日15:06:18
 */
@Slf4j
@Configuration
public class AsyncTaskExecutePool implements AsyncConfigurer {

    /**
     * 注入配置类
     */
    private final AsyncTaskProperties config;

    public AsyncTaskExecutePool(AsyncTaskProperties config) {
        this.config = config;
    }

    @Override
    public Executor getAsyncExecutor() {
        log.debug("Creating Async Task Executor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程池大小
        executor.setCorePoolSize(config.getCorePoolSize());
        // 最大线程数
        executor.setMaxPoolSize(config.getMaxPoolSize());
        // 队列容量
        executor.setQueueCapacity(config.getQueueCapacity());
        // 活跃时间
        executor.setKeepAliveSeconds(config.getKeepAliveSeconds());
        // 线程名字前缀,在有多个线程池的时候以此做区分
        executor.setThreadNamePrefix("blog-async-");
        // 传递上下文等变量
        executor.setTaskDecorator(new ContextDecorator());
        // setRejectedExecutionHandler：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> {
            log.error("====" + throwable.getMessage() + "====", throwable);
            log.error("exception method:" + method.getName());
        };
    }
}

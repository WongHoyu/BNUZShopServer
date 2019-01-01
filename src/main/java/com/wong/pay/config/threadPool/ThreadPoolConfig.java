package com.wong.pay.config.threadPool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
@ComponentScan("com.wong.pay.config.threadPool")
public class ThreadPoolConfig {

    @Value("${thread.threadNamePrefix}")
    private String threadNamePrefix;

    @Value("${thread.maxPoolSize}")
    private int maxPoolSize;

    @Value("${thread.corePoolSize}")
    private int coreSize;

    @Value("${thread.queueCapacity}")
    private int queueCapacity;

    @Value("${thread.keepAliveSeconds}")
    private int keepAliveSeconds;

    /*
     * 订单线程池
     */
    @Bean
    public Executor createOrderExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setCorePoolSize(coreSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

}

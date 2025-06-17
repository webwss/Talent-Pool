package cn.webwss.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig {

    @Bean("generateQuestionsTaskExecutor")
    public ThreadPoolTaskExecutor generateQuestionsTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(2); //核心线程数
        threadPoolTaskExecutor.setMaxPoolSize(4); // 最大线程数
        threadPoolTaskExecutor.setKeepAliveSeconds(60); // 线程空闲时间
        threadPoolTaskExecutor.setQueueCapacity(100); // 队列大小
        threadPoolTaskExecutor.setThreadNamePrefix("Thread-"); // 线程前缀名
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // 拒绝策略
        threadPoolTaskExecutor.initialize(); // 初始化
        return threadPoolTaskExecutor;
    }


}

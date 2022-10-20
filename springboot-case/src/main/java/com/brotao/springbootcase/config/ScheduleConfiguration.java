package com.brotao.springbootcase.config;

import com.brotao.springbootcase.util.ThreadUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.ExecutorService;

/**
 * @Author luotao
 * @Date 2022-07-04 13:51
 * @Description
 */

@EnableScheduling
@Configuration
@EnableAsync
public class ScheduleConfiguration {

    @Bean("scheduledExecutor")
    public ExecutorService executorService() {
        return ThreadUtil.getThreadPoolExecutor();
    }

}

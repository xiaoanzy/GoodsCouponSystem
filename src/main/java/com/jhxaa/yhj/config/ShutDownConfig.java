package com.jhxaa.yhj.config;


import com.jhxaa.yhj.system.CloseSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShutDownConfig {

    @Bean
    public CloseSystem getTerminateBean() {
        return new CloseSystem();
    }
}

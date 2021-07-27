package com.korea.dbapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 설정같은것들을 메모리에 띄울 때 사용
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new sessionintercepter())
                // 이렇게 해놓으면 /post로 요청하는 모든 uri를 차단
                .addPathPatterns("/post/**");
    }

}

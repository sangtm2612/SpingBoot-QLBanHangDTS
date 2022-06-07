package com.example.sangtmph17730_asm.config;

import com.example.sangtmph17730_asm.Interceptor.AuthenticationInterceptor;
import com.example.sangtmph17730_asm.Interceptor.LayoutGuestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthenticationInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/admin/**");
        registry.addInterceptor(new LayoutGuestInterceptor()).addPathPatterns("/home/**");
    }
}

package com.cgm.tmao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//配置类，用于允许所有的请求都跨域。
//因为是二次请求，第一次是获取 html 页面，
// 第二次通过 html 页面上的 js 代码异步获取数据，
// 一旦部署到服务器就容易面临跨域请求问题，
// 所以允许所有访问都跨域，就不会出现通过 ajax 获取数据获取不到的问题了。
@Configuration
//@EnableWebMvc
public class CORSConfiguration extends WebMvcConfigurerAdapter{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("static/**").addResourceLocations("classpath:static/");
    }

//    //解决springboot PUT请求接收不到参数问题
//    @Bean
//    public HttpPutFormContentFilter httpPutFormContentFilter() {
//        return new HttpPutFormContentFilter();
//    }
}



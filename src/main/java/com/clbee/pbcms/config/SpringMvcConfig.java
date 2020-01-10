package com.clbee.pbcms.config;

import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {

    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/webapp/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/webapp/js/");
        registry.addResourceHandler("/images/**").addResourceLocations("/resources/webapp/images/");
        registry.addResourceHandler("/font/**").addResourceLocations("/resources/webapp/font/");
    }
    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public RequestMappingHandlerAdapter noname() {
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();

        List converters = new ArrayList();
        converters.add(new MappingJackson2HttpMessageConverter());
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(new SourceHttpMessageConverter());
        converters.add(new FormHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());

        requestMappingHandlerAdapter.setMessageConverters(converters);
        return requestMappingHandlerAdapter;
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        // 메세지 프로퍼티파일의 위치와 이름을 지정한다.
        source.setBasename("classpath:/messages/messages");
        // 기본 인코딩을 지정한다.
        source.setDefaultEncoding("UTF-8");
        // 프로퍼티 파일의 변경을 감지할 시간 간격을 지정한다.
        source.setCacheSeconds(60);
        // 없는 메세지일 경우 예외를 발생시키는 대신 코드를 기본 메세지로 한다.
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }

    @Bean
    public SessionLocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

}
package com.blueocn.api.support.config;

import com.blueocn.api.support.csrf.CSRFHandlerInterceptor;
import com.blueocn.api.support.session.SessionHandlerInterceptor;
import com.google.common.base.Charsets;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver;

import java.util.List;

import static com.blueocn.api.support.Constants.SYSTEM_CONF_PROPERTIES;

/**
 * Title: WebConfig
 * Description: Spring MVC 配置类, 和 Spring 的配置隔离.
 *
 * @author Yufan
 * @version 1.0.0
 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
 * @since 2015-12-20 19:44
 */
@Configuration
@ComponentScan(basePackages = "com.blueocn.api.controller")
public class WebConfig extends WebMvcConfigurationSupport implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource(SYSTEM_CONF_PROPERTIES));
        return configurer;
    }

    @Bean(name = "byteArrayHttpMessageConverter")
    ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
        return new ByteArrayHttpMessageConverter();
    }

    @Bean(name = "stringHttpMessageConverter")
    StringHttpMessageConverter stringHttpMessageConverter() {
        return new StringHttpMessageConverter(Charsets.UTF_8);
    }

    @Bean(name = "resourceHttpMessageConverter")
    ResourceHttpMessageConverter resourceHttpMessageConverter() {
        return new ResourceHttpMessageConverter();
    }

    @Bean(name = "mappingJackson2HttpMessageConverter")
    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }

    @Bean(name = "sourceHttpMessageConverter")
    SourceHttpMessageConverter sourceHttpMessageConverter() {
        return new SourceHttpMessageConverter();
    }

    @Bean(name = "jaxb2RootElementHttpMessageConverter")
    Jaxb2RootElementHttpMessageConverter jaxb2RootElementHttpMessageConverter() {
        return new Jaxb2RootElementHttpMessageConverter();
    }

    /**
     * I don't like to Autowired this HandleInterceptor for test consideration.
     */
    @Bean(name = "sessionHandlerInterceptor")
    public SessionHandlerInterceptor sessionHandlerInterceptor() {
        return new SessionHandlerInterceptor();
    }

    @Bean(name = "csrfHandlerInterceptor")
    public CSRFHandlerInterceptor csrfHandlerInterceptor() {
        return new CSRFHandlerInterceptor();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(byteArrayHttpMessageConverter());
        converters.add(stringHttpMessageConverter());
        converters.add(resourceHttpMessageConverter());
        converters.add(mappingJackson2HttpMessageConverter());
        converters.add(sourceHttpMessageConverter());
        converters.add(jaxb2RootElementHttpMessageConverter());
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(sessionHandlerInterceptor());
        registry.addInterceptor(csrfHandlerInterceptor());
    }

    @Bean(name = "velocityConfig")
    public VelocityConfigurer velocityConfig() {
        VelocityConfigurer configurer = new VelocityConfigurer();
        configurer.setConfigLocation(resourceLoader.getResource("/WEB-INF/views/velocity.properties"));
        return configurer;
    }

    @Bean(name = "viewResolver")
    public VelocityLayoutViewResolver viewResolver() {
        VelocityLayoutViewResolver velocityLayoutViewResolver = new VelocityLayoutViewResolver();
        velocityLayoutViewResolver.setCache(false);
        velocityLayoutViewResolver.setPrefix("/templates/");
        velocityLayoutViewResolver.setLayoutUrl("/layout/layout.vm");
        velocityLayoutViewResolver.setSuffix(".vm");
        velocityLayoutViewResolver.setExposeSpringMacroHelpers(true);
        velocityLayoutViewResolver.setContentType("text/html;charset=UTF-8");
        // @see AbstractTemplateView#setExposeSpringMacroHelpers
        velocityLayoutViewResolver.setExposeSpringMacroHelpers(true);
        // 定义是否暴露Spring的RequestContext对象暴露为变量rc, 便于获取程序路径
        velocityLayoutViewResolver.setRequestContextAttribute("rc");
        velocityLayoutViewResolver.setViewClass(org.springframework.web.servlet.view.velocity.VelocityLayoutView.class);
        velocityLayoutViewResolver.setToolboxConfigLocation("/WEB-INF/toolbox.xml");
        return velocityLayoutViewResolver;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}

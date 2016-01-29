package com.blueocn.api.support.config;

import com.google.common.base.Charsets;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Title: MailConfig
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-08 22:41
 */
@Configuration
public class MailConfig implements ResourceLoaderAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailConfig.class);
    private ResourceLoader resourceLoader;

    @Bean
    public JavaMailSender javaMailSender(
        @Value("${mail.host}")
        String mailHost,
        @Value("${mail.port}")
        int mailPort,
        @Value("${mail.username}")
        String mailUsername,
        @Value("${mail.password}")
        String mailPassword) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setPort(mailPort);
        mailSender.setUsername(mailUsername);
        mailSender.setPassword(mailPassword);
        mailSender.setDefaultEncoding(Charsets.UTF_8.displayName());
        mailSender.setJavaMailProperties(getJavaMailProperties());

        return mailSender;
    }

    private Properties getJavaMailProperties() {
        try {
            return PropertiesLoaderUtils.loadProperties(resourceLoader.getResource("classpath:/javaMail.properties"));
        } catch (IOException e) {
            LOGGER.info("", e);
        }
        return new Properties();
    }

    @Bean(name = "velocityEngine")
    public VelocityEngine velocityEngine() throws IOException {
        VelocityEngineFactory factory = new VelocityEngineFactory();
        factory.setConfigLocation(resourceLoader.getResource("classpath:/template.properties"));
        return factory.createVelocityEngine();
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}

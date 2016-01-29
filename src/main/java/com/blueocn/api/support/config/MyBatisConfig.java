package com.blueocn.api.support.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Title: MyBatisConfig
 * Description: MyBatis 注解配置类, 从配置文件获取数据源加载指定数据源
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-24 21:53
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.blueocn.api.repository.mapper", annotationClass = org.springframework.stereotype.Repository.class)
public class MyBatisConfig implements ApplicationContextAware {
    private ApplicationContext context;

    @Autowired
    private DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("com.blueocn.api.repository.domain");
        factoryBean.setMapperLocations(context.getResources("classpath:mybatis/mapper/*.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }
}

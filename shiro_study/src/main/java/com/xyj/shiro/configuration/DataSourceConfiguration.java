package com.xyj.shiro.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.xyj.shiro.util.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

/**
 * Created by banma on 2017/6/23.
 */
@Configuration
public class DataSourceConfiguration implements EnvironmentAware {

    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
    }

    @Bean(destroyMethod = "close", initMethod = "init")
    public DataSource writeDataSource() throws Exception{
        LogUtils.log4j.info("注入druid!");
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(propertyResolver.getProperty("url"));
        dataSource.setDriverClassName(propertyResolver.getProperty("driver-class-name"));
        dataSource.setUsername(propertyResolver.getProperty("username"));
        dataSource.setPassword(propertyResolver.getProperty("password"));
        //配置监控统计拦截的filters,去掉之后监控界面的sql无法统计
        dataSource.setFilters("stat,log4j2,wall");
        return dataSource;
    }

}

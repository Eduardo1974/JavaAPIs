package com.example.demo.service.locator.example1;

import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by eduardo on 21/12/17.
 */
@Configuration
@ComponentScan("com.example.demo")
public class Config {

    @Bean
    public ServiceLocatorFactoryBean serviceLocatorBean(){
        ServiceLocatorFactoryBean bean = new ServiceLocatorFactoryBean();
        bean.setServiceLocatorInterface(ShapeFactory.class);
        return bean;
    }
}

package com.example.demo.service.locator.example1;

import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by eduardo on 21/12/17.
 */
public interface ShapeFactory {

    Shape getShape(String name);

}


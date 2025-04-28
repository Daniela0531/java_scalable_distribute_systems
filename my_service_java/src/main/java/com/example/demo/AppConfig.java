package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

//@PropertySource("application.properties")
//@ConfigurationProperties(prefix = "spring.datasource")
public class AppConfig {
//    @Value("${url}")
    private String datasourceUrl;
}

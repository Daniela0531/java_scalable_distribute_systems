package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class AppConfig {
    @Bean(name = "shard1DataSource")
    @ConfigurationProperties("spring.datasource.shard1")
    public DataSource shard1DataSource() {
        return new DriverManagerDataSource();
    }

    @Bean(name = "shard2DataSource")
    @ConfigurationProperties("spring.datasource.shard2")
    public DataSource shard2DataSource() {
        return new DriverManagerDataSource();
    }
}


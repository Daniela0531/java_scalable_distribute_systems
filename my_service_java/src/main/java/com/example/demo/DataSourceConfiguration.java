package com.example.demo;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })


@Configuration
public class DataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.shard1")
    public DataSourceProperties shard1DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.shard2")
    public DataSourceProperties shard2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource shard1DataSource() {
        return shard1DataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public DataSource shard2DataSource() {
        return shard2DataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Primary
    @Bean(name = "shard1JdbcTemplate")
    public JdbcTemplate shard1JdbcTemplate() {
        return new JdbcTemplate(shard1DataSource());
    }

    @Bean(name = "shard2JdbcTemplate")
    public JdbcTemplate shard2JdbcTemplate() {
        return new JdbcTemplate(shard2DataSource());
    }
}
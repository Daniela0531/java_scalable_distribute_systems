package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean(name = "shard1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.shard1")
    public DataSource  shard1DataSource() {
        return new DriverManagerDataSource();
    }
    @Bean(name = "shard1JdbcTemplate")
    public JdbcTemplate shard1JdbcTemplate(@Qualifier("shard1DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

//    @Bean(name = "shard2DataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.shard2")
//    public DataSource shard2DataSource() {
//        return new DriverManagerDataSource();
//    }
//    @Bean(name = "shard2JdbcTemplate")
//    public JdbcTemplate shard2JdbcTemplate(@Qualifier("shard2DataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
}

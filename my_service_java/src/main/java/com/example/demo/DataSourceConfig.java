package com.example.demo;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {
    @Value("spring.datasource.shard1.url")
    private String shard1Url;
    @Value("spring.datasource.shard1.username")
    private String shard1Username;
    @Value("spring.datasource.shard1.password")
    private String shard1Password;
    @Value("spring.datasource.shard2.url")
    private String shard2Url;
    @Value("spring.datasource.shard2.username")
    private String shard2Username;
    @Value("spring.datasource.shard2.password")
    private String shard2Password;
    @Bean(name = "shard1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.shard1")
    public DataSource  shard1DataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(shard1Url);
        dataSource.setUsername(shard1Username);
        dataSource.setPassword(shard1Password);
        return dataSource;
    }
    @Bean(name = "shard1JdbcTemplate")
    public JdbcTemplate shard1JdbcTemplate(@Qualifier("shard1DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "shard2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.shard2")
    public DataSource  shard2DataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(shard2Url);
        dataSource.setUsername(shard2Username);
        dataSource.setPassword(shard2Password);
        return dataSource;
    }
    @Bean(name = "shard2JdbcTemplate")
    public JdbcTemplate shard2JdbcTemplate(@Qualifier("shard2DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(shard1DataSource())
//                .packages("com.example.demo")
//                .persistenceUnit("yourPersistenceUnit")
//                .build();
//    }

    @Primary
    @Bean(name = "shard1EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean shard1EntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(shard1DataSource())
                .packages("com.example.demo") // Пакет с сущностями
                .persistenceUnit("shard1")
                .properties(new HashMap<>())
                .build();
    }

    @Primary
    @Bean(name = "shard1TransactionManager")
    public PlatformTransactionManager shard1TransactionManager(
            @Qualifier("shard1EntityManagerFactory") EntityManagerFactory shard1EntityManagerFactory) {
        return new JpaTransactionManager(shard1EntityManagerFactory);
    }

//    @Primary
//    @Bean(name = "shard2EntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean shard1EntityManagerFactory(
//            EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(shard1DataSource())
//                .packages("com.example.demo") // Пакет с сущностями
//                .persistenceUnit("shard1")
//                .properties(new HashMap<>())
//                .build();
//    }
//
//    @Primary
//    @Bean(name = "shard2TransactionManager")
//    public PlatformTransactionManager shard1TransactionManager(
//            @Qualifier("shard1EntityManagerFactory") EntityManagerFactory shard1EntityManagerFactory) {
//        return new JpaTransactionManager(shard1EntityManagerFactory);
//    }


}

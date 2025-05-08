package com.example.demo;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class DataRepository {
    @Autowired
    @Qualifier("shard1JdbcTemplate")
    private JdbcTemplate jdbcTemplateShard1;
    @Autowired
    @Qualifier("shard2JdbcTemplate")
    private JdbcTemplate jdbcTemplateShard2;

    public DataRepository(JdbcTemplate jdbcTemplates1, JdbcTemplate jdbcTemplates2) {
        this.jdbcTemplateShard1 = jdbcTemplates1;
        this.jdbcTemplateShard2 = jdbcTemplates2;
    }

    private JdbcTemplate chooseShoulder(String key) {
        if (key.hashCode() % 2 == 0) {
            return jdbcTemplateShard1;
        }
        return jdbcTemplateShard2;
    }

    @PostConstruct
    public void init() {
        jdbcTemplateShard1.execute("""
                   CREATE TABLE IF NOT EXISTS key_value (
                   key VARCHAR PRIMARY KEY,
                   value VARCHAR NOT NULL
                   );
                """);
        jdbcTemplateShard2.execute("""
                   CREATE TABLE IF NOT EXISTS key_value (
                   key VARCHAR PRIMARY KEY,
                   value VARCHAR NOT NULL
                   );
                """);
    }

    public void put(String key, String value) {
        chooseShoulder(key).update("""
                INSERT INTO key_value (
                key, value) VALUES (
                ?, ?)
                ON CONFLICT(key) DO UPDATE SET value = ?;
                """, key, value, value);
    }

    public Data get(String key) {
        SqlRowSet resultSet = chooseShoulder(key).queryForRowSet("""
             SELECT * FROM key_value WHERE key = ?;
         """, key);
        if (resultSet.next()) {
//            System.out.println(String.format("        my System.out.println(key) key = %s", resultSet.getString("key")));
            return new Data(resultSet.getString("key"), resultSet.getString("value"));
        }
        return null;
    }
}
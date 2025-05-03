package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;
import javax.sql.DataSource;


@Service
public class DataService {
    private static final Logger logger = Logger.getLogger(DataService.class.getName());
    private final Integer shardNumber = 2;
    private final JdbcTemplate shard1JdbcTemplate;
//    private final JdbcTemplate shard2JdbcTemplate;
//    private final DataSource shard1DataSource;


    public DataService(DataRepository repository, @Qualifier("shard1JdbcTemplate") JdbcTemplate shard1JdbcTemplate, DataSource shard1DataSource) {
        this.shard1JdbcTemplate = shard1JdbcTemplate;
    }

//    public String testConnection() {
//        try (Connection connection = shard1DataSource.getConnection()) {
//            return "Connected to shard 1: " + connection.getMetaData().getURL();
//        } catch (SQLException e) {
//            return "Connection failed: " + e.getMessage();
//        }
//    }

//    @Autowired
//    public DataService(
//            @Qualifier("shard1JdbcTemplate") JdbcTemplate shard1JdbcTemplate,
//            @Qualifier("shard2JdbcTemplate") JdbcTemplate shard2JdbcTemplate) {
//        this.shard1JdbcTemplate = shard1JdbcTemplate;
//        this.shard2JdbcTemplate = shard2JdbcTemplate;
//    }

    public Optional<Data> findById(String key) {
        String sql = String.format("SELECT * FROM shard1 WHERE key = %s)", key);
        switch (Integer.getInteger(key) % shardNumber) {
            case 0:
//                String sql = "SELECT value FROM your_table WHERE key = ?";
//                return shard1JdbcTemplate.queryForObject(sql, new Object[]{key}, String.class);
//        }

        return Optional.of(shard1JdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Data(
                        rs.getString("key"),
                        rs.getString("value")
                )));
//            case 1:
//                return Optional.of(shard2JdbcTemplate.queryForObject(sql, new RowMapper<Data>() {
//                    @Override
//                    public Data mapRow(ResultSet rs, int rowNum) throws SQLException {
//                        return new Data(
//                                rs.getString("key"),
//                                rs.getString("value")
//                        );
//                    }
//                }));
        }

        logger.info("Can't find element");
        return Optional.of(new Data());
//        return repository.findById(key);
    }


    public void saveData(Data data) {
        if (data.hashCode() % shardNumber == 0) {
            String sql = String.format("INSERT INTO shard1 (key, value) VALUES (%s, %s)", data.getKey(), data.getValue());
            shard1JdbcTemplate.update(sql);
        }
//        else {
//            shard2JdbcTemplate.update("INSERT INTO shard2 (key, value) VALUES (?, ?)", data.getKey(), data.getValue());
//        }
//        repository.save(data);
    }
}
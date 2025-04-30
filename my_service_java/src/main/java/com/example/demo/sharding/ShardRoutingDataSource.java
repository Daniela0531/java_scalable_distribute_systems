package com.example.demo.sharding;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class ShardRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        // Логика для определения текущего шарда на основе контекста запроса
        // Например, вы можете использовать ThreadLocal или другие методы для хранения текущего ключа
        return ShardContextHolder.getCurrentShard();
    }
}

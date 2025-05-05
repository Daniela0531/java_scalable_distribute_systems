package com.example.demo.shard1;
//import javax.persistence.Entity;
//import javax.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DataShard1 {
    @Id
    private String key;
    private String value;

    // Конструкторы, геттеры и сеттеры

    public DataShard1() {
        this.key = "";
        this.value = "";
    }

    public DataShard1(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

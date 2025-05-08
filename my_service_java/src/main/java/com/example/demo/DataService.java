package com.example.demo;

//import com.example.demo.shard2.DataRepositoryShard2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;


@Service
public class DataService {
    private static final Logger logger = Logger.getLogger(DataService.class.getName());
    private final Integer shardNumber = 2;
    private final DataRepository keyValueRepository;

    public DataService(DataRepository keyValueRepository) {
        this.keyValueRepository = keyValueRepository;
    }
    public Optional<Data> findById(String key) {
        return Optional.ofNullable(keyValueRepository.get(key));
    }


    public void saveData(Data data) {
        System.out.println("qwertyuiop");
        keyValueRepository.put(data.getKey(), data.getValue());
    }
}
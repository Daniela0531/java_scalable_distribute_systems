package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataService {
    @Autowired
    private Repository repository;

    public Optional<Data> get(String key) {
        return repository.findById(key);
    }

    public void put(Data data) {
        repository.save(data);
    }
}

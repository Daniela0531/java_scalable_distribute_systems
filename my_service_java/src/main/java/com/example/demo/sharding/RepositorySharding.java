package com.example.demo.sharding;
import com.example.demo.Data;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RepositorySharding {
        @Autowired
        private EntityManager entityManager;

        @Transactional
        public void save(Data data) {
            entityManager.persist(data);
        }

        public Data findById(String key) {
            return entityManager.find(Data.class, key);
        }
    }


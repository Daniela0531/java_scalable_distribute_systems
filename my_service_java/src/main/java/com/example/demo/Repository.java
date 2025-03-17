package com.example.demo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Data, String> {
}

package com.example.demo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class Controller {
    private final DataService service;

    public Controller(DataService dataService) {
        this.service = dataService;
    }


    @GetMapping("/get")
    public ResponseEntity<String> getValue(@RequestParam(required = false) String key) {
        Optional<Data> data = service.findById(key);
        return data.map(value -> ResponseEntity.ok("Value for " + key + ": " + value.getValue()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/put")
    public ResponseEntity<String> putValue(@RequestParam(required = false)  String key, @RequestParam(required = false)  String value) {
        if (key == null || value == null) {
            return ResponseEntity.ok("Nothing to put");
        }
        Data data = new Data(key, value);
        service.saveData(data);
        return ResponseEntity.ok("PUT (key, value): (" + key + ", " + value + ")");
    }
}


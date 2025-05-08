package com.example.demo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class Controller {
    private final DataService service;

    public Controller(DataService dataService) {
        this.service = dataService;
    }

    @GetMapping("/")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("I am working");
    }

    @GetMapping("/get")
    public ResponseEntity<String> getValue(@RequestParam(required = false) String key) {
        Optional<Data> data = service.findById(key);
        System.out.println(String.format("        my System.out.println(key) key = %s", key));
        return data.map(value -> ResponseEntity.ok("Value for " + key + ": " + value.getValue()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/put")
    public ResponseEntity<String> putValue(@RequestParam(required = false) String key, @RequestParam(required = false) String value) {
        if (key == null || value == null) {
            return ResponseEntity.ok("Nothing to put");
        }
        System.out.println("         @PutMapping(\"/put\")");
        Data data = new Data(key, value);
        service.saveData(data);
        return ResponseEntity.ok("PUT (key, value): (" + key + ", " + value + ")");
    }
}


package com.example.demo;
import com.example.demo.shard2.DataShard2;
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
        Optional<DataShard2> data = service.findById(key);
        return data.map(value -> ResponseEntity.ok("Value for " + key + ": " + value.getValue()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/put")
    public ResponseEntity<String> putValue(@RequestParam(required = false)  String key, @RequestParam(required = false)  String value) {
        if (key == null || value == null) {
            return ResponseEntity.ok("Nothing to put");
        }
        DataShard2 data = new DataShard2(key, value);
        service.saveData(data);
        return ResponseEntity.ok("PUT (key, value): (" + key + ", " + value + ")");
    }
}


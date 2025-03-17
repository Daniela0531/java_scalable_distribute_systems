package com.example.demo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MyController {

    private final Map<String, String> dataStore = new HashMap<>();

    @GetMapping("/get")
    public ResponseEntity<String> getValue(@RequestParam(required = false) String key) {
        String value = dataStore.get(key);
        if (value != null) {
            return ResponseEntity.ok("GET value = " + value + " by key = " + key);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @RequestBody
    @PutMapping("/put")
    public ResponseEntity<String> putValue(@RequestParam(required = false)  String key, @RequestParam(required = false)  String value) {
        if (key == null || value == null) {
            return ResponseEntity.ok("Nothing to put");
        }
        dataStore.put(key, value);
        return ResponseEntity.ok("PUT (key, value): (" + key + ", " + value + ")");
    }

}


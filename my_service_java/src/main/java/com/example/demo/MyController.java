package com.example.demo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MyController {

    private final Map<String, String> dataStore = new HashMap<>();

    @GetMapping("/get/{key}")
    public String getValue(@PathVariable String key) {
        return dataStore.getOrDefault(key, "Not Found heeey");
    }

    @PutMapping("/put/{key}")
    public ResponseEntity<String> putValue(@PathVariable String key, @RequestBody String value) {
        dataStore.put(key, value);
        return ResponseEntity.ok("Saved value for key: " + key);
    }

}


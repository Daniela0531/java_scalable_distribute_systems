package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    private Repository repository;

//    private final Map<String, String> dataStore = new HashMap<>();
//@PostMapping("/put")
//public ResponseEntity<String> putValue(@RequestParam String key, @RequestParam String value) {
//    KeyValue keyValue = new KeyValue(key, value);
//    keyValueRepository.save(keyValue);
//    return ResponseEntity.ok("Saved: " + key + " = " + value);
//}

//    @GetMapping("/get")
//    public ResponseEntity<String> getValue(@RequestParam String key) {
//        Optional<KeyValue> keyValue = keyValueRepository.findById(key);
//        return keyValue.map(value -> ResponseEntity.ok("Value for " + key + ": " + value.getValue()))
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @GetMapping("/get")
    public ResponseEntity<String> getValue(@RequestParam(required = false) String key) {
        Optional<Data> data = repository.findById(key);
        return data.map(value -> ResponseEntity.ok("Value for " + key + ": " + value.getValue()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @RequestBody
    @PutMapping("/put")
    public ResponseEntity<String> putValue(@RequestParam(required = false)  String key, @RequestParam(required = false)  String value) {
        if (key == null || value == null) {
            return ResponseEntity.ok("Nothing to put");
        }
        Data data = new Data(key, value);
        repository.save(data);
        return ResponseEntity.ok("PUT (key, value): (" + key + ", " + value + ")");
    }

}


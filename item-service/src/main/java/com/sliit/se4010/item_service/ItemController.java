package com.sliit.se4010.item_service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    // Simple in-memory list (no database needed) as per lab sheet
    private List<String> items = new ArrayList<>(List.of("Book", "Laptop", "Phone")); 

    // GET /items - Returns list of all items
    @GetMapping
    public List<String> getItems() {
        return items; 
    }

    // POST /items - Create a new item (Required for bonus marks)
    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody String item) {
        items.add(item); 
        return ResponseEntity.status(HttpStatus.CREATED).body("Item added: " + item); 
    }

    // GET /items/{id} - Get item by ID
    @GetMapping("/{id}")
    public ResponseEntity<String> getItem(@PathVariable int id) {
        if (id < 0 || id >= items.size()) {
            return ResponseEntity.notFound().build(); 
        }
        return ResponseEntity.ok(items.get(id)); 
    }
}
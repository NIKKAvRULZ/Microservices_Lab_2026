package com.sliit.se4010.payment_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    
    private List<Map<String, Object>> payments = new ArrayList<>();
    private int idCounter = 1;

    // GET /payments - Returns all payments
    @GetMapping
    public List<Map<String, Object>> getPayments() {
        return payments;
    }

    // POST /payments/process - Process a payment
    @PostMapping("/process")
    public ResponseEntity<Map<String, Object>> processPayment(@RequestBody Map<String, Object> payment) {
        payment.put("id", idCounter++);
        payment.put("status", "SUCCESS");
        payments.add(payment);
        return ResponseEntity.status(201).body(payment);
    }

    // GET /payments/{id} - Get payment status by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPayment(@PathVariable int id) {
        return payments.stream()
            .filter(p -> p.get("id").equals(id))
            .findFirst()
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    } 
}
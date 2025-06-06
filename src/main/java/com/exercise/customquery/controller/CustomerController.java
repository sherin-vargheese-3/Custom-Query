package com.exercise.customquery.controller;

import com.exercise.customquery.model.Customer;
import com.exercise.customquery.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    public final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<?> addCustomerWithOrders(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                customerService.addCustomerWithOrders(customer));
    }

    @GetMapping("/top")
    public ResponseEntity<?> getTopSpendingCustomers(@RequestParam int limit) {
        return ResponseEntity.ok(customerService.getTopSpendingCustomers(limit));
    }

 }

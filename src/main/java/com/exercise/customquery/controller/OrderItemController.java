package com.exercise.customquery.controller;

import com.exercise.customquery.service.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/popular")
    public ResponseEntity<?> getMostPopularItem() {
        return ResponseEntity.ok(orderItemService.findMostPopularItem());
    }
}

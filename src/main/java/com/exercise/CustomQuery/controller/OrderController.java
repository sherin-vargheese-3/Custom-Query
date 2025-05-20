package com.exercise.CustomQuery.controller;

import com.exercise.CustomQuery.service.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/sales")
    public ResponseEntity<?> getSalesDuring(@RequestParam
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                            @RequestParam(required = false, defaultValue = "") String to
    ) {
        LocalDate toDate = (to == null || to.isBlank() ? LocalDate.now() : LocalDate.parse(to, DateTimeFormatter.ISO_LOCAL_DATE));

        return ResponseEntity.ok(orderService.findOrderDuring(from, toDate));
    }

    @GetMapping("/average-order-value")
    public ResponseEntity<?> getAverageOrderValue() {
        return ResponseEntity.ok(String.format("""
				{
					"Average Order Value" : "%f"
				}""", orderService.findAverageOrderValue()));
    }
}

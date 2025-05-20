package com.exercise.CustomQuery.service;

import com.exercise.CustomQuery.contract.OrderDTO;
import com.exercise.CustomQuery.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDTO> findOrderDuring(LocalDate from, LocalDate to) {
        return orderRepository.findByOrderDateBetween(from, to)
                .stream()
                .map(order -> OrderDTO.builder()
                        .id(order.getId())
                        .orderDate(order.getOrderDate())
                        .itemsCount(order.getOrderItems().size())
                        .customerId(order.getCustomer().getId())
                        .build()
                ).toList();
    }

    public Double findAverageOrderValue() {
        if (orderRepository.count() == 0) {
            return 0.0;
        }
        return orderRepository.calcAverageOrderValue();
    }
}

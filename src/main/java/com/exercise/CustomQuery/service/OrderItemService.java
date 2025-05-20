package com.exercise.CustomQuery.service;

import com.exercise.CustomQuery.contract.MostPopularItem;
import com.exercise.CustomQuery.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<MostPopularItem> findMostPopularItem() {
        return orderItemRepository.findMostPopularItems();
    }
}

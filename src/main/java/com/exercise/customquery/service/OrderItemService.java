package com.exercise.customquery.service;

import com.exercise.customquery.contract.MostPopularItem;
import com.exercise.customquery.repository.OrderItemRepository;
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

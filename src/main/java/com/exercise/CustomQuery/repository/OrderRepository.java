package com.exercise.CustomQuery.repository;

import com.exercise.CustomQuery.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByOrderDateBetween(LocalDate from, LocalDate to);

    @Query(value = "SELECT AVG(order_totals.total_order_value)" +
            " FROM (" +
            " SELECT SUM(oi.price * oi.quantity) AS total_order_value" +
            " FROM orders o" +
            " JOIN order_items oi ON o.id = oi.order_id" +
            " GROUP BY o.id" +
            " ) AS order_totals"
            , nativeQuery = true)
    Double calcAverageOrderValue();
}

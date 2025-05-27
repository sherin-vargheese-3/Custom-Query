package com.exercise.customquery.repository;

import com.exercise.customquery.contract.MostPopularItem;
import com.exercise.customquery.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query(value = " SELECT product_name AS productName, " +
            " price," +
            " SUM(quantity) AS totalQuantitySold," +
            " SUM(price * quantity) AS totalRevenue" +
            " from order_items" +
            " GROUP BY productName, price" +
            " ORDER BY SUM(quantity) DESC"
//            " LIMIT :limit"
            , nativeQuery = true)
    List<MostPopularItem> findMostPopularItems();
}

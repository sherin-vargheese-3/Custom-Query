package com.exercise.CustomQuery.repository;

import com.exercise.CustomQuery.contract.CustomerSummary;
import com.exercise.CustomQuery.model.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c.name AS name, SUM(oi.quantity * oi.price) AS totalSpent " +
            "FROM Customer c JOIN c.orders o JOIN o.orderItems oi " +
            "GROUP BY c.id, c.name " +
            "ORDER BY totalSpent DESC")
    List<CustomerSummary> findTopSpendingCustomers(Pageable pageable);
}

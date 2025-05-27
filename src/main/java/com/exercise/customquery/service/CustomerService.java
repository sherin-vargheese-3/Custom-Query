package com.exercise.customquery.service;

import com.exercise.customquery.contract.CustomerDTO;
import com.exercise.customquery.contract.CustomerSummary;
import com.exercise.customquery.model.Customer;
import com.exercise.customquery.model.Order;
import com.exercise.customquery.model.OrderItem;
import com.exercise.customquery.repository.CustomerRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDTO addCustomerWithOrders(Customer customer) {
        if (customer.getOrders() != null) {
            for (Order order : customer.getOrders()) {
                order.setCustomer(customer);
                if (order.getOrderItems() != null) {
                    for (OrderItem item : order.getOrderItems()) {
                        item.setOrder(order);
                    }
                }
            }
        }
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerDTO.builder()
                .id(savedCustomer.getId())
                .name(savedCustomer.getName())
                .email(savedCustomer.getEmail())
                .totalOrderCount(savedCustomer.getOrders().size())
                .build();
    }

    public List<CustomerSummary> getTopSpendingCustomers(int limit) {
        return customerRepository.findTopSpendingCustomers(PageRequest.of(0, limit));
    }
}

package com.example.etickets.service;

import com.example.etickets.entity.Order;
import com.example.etickets.repository.CustomerRepository;
import com.example.etickets.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public void addOrder(Order order, int customerId) throws NoSuchElementException {
        var customer = customerRepository.getCustomerById(customerId);

        if (customer.isPresent()) {
            order.setCustomer(customer.get());
            orderRepository.save(order);
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(int id) {
        return orderRepository.getOrderById(id);
    }

    public BigDecimal getOrderTotalById(int id) {
        return orderRepository.getOrderTotalById(id);
    }

    public void updateDateById(Date orderDate, int id) throws NoSuchElementException {
        var order = getOrderById(id);

        if (order.isPresent()) {
            orderRepository.updateDateById(orderDate, id);
        } else {
            throw new NoSuchElementException();
        }
    }

    public void deleteOrderById(int id) throws NoSuchElementException {
        var order = getOrderById(id);

        if (order.isPresent()) {
            orderRepository.deleteOrderById(id);
        } else {
            throw new NoSuchElementException();
        }
    }
}

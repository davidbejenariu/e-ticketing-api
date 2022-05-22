package com.example.etickets.controller;

import com.example.etickets.entity.Order;
import com.example.etickets.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order/{customer-id}")
    public ResponseEntity<Void> addOrder(@RequestBody Order order, @PathVariable("customer-id") int customerId) {
        try {
            orderService.addOrder(order, customerId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") int id) {
        var order = orderService.getOrderById(id);

        if (order.isPresent()) {
            return ResponseEntity.of(order);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/order/{id}/total")
    public BigDecimal getOrderTotalById(@PathVariable("id") int id) {
        return orderService.getOrderTotalById(id);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<Void> updateDateById(@RequestParam Date orderDate, @PathVariable("id") int id) {
        try {
            orderService.updateDateById(orderDate, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable("id") int id) {
        try {
            orderService.deleteOrderById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

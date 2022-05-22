package com.example.etickets.controller;

import com.example.etickets.entity.OrderTicket;
import com.example.etickets.service.OrderTicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class OrderTicketController {
    private final OrderTicketService orderTicketService;

    @PostMapping("/order-ticket/{order-id}-{ticket-id}")
    public ResponseEntity<Void> addOrderTicket(@RequestBody OrderTicket orderTicket,
                                               @PathVariable("order-id") int orderId,
                                               @PathVariable("ticket-id") int ticketId) {
        try {
            orderTicketService.addOrderTicket(orderTicket, orderId, ticketId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/order-tickets")
    public List<OrderTicket> getAllOrderTickets() {
        return orderTicketService.getAllOrderTickets();
    }

    @GetMapping("/order-ticket/{order-id}-{ticket-id}")
    public ResponseEntity<OrderTicket> getOrderTicketById(@PathVariable("order-id") int orderId,
                                                          @PathVariable("ticket-id") int ticketId) {
        var orderTicket = orderTicketService.getOrderTicketById(orderId, ticketId);

        if (orderTicket.isPresent()) {
            return ResponseEntity.of(orderTicket);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/order-ticket/{order-id}-{ticket-id}")
    public ResponseEntity<Void> updateQuantityById(@RequestParam int quantity,
                                                   @PathVariable("order-id") int orderId,
                                                   @PathVariable("ticket-id") int ticketId) {
        try {
            orderTicketService.updateQuantityById(quantity, orderId, ticketId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/order-ticket/{order-id}-{ticket-id}")
    public ResponseEntity<Void> deleteOrderTicketById(@PathVariable("order-id") int orderId,
                                                      @PathVariable("ticket-id") int ticketId) {
        try {
            orderTicketService.deleteOrderTicketById(orderId, ticketId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

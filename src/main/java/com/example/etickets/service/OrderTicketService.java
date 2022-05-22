package com.example.etickets.service;

import com.example.etickets.entity.OrderTicket;
import com.example.etickets.repository.OrderRepository;
import com.example.etickets.repository.OrderTicketRepository;
import com.example.etickets.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class OrderTicketService {
    private final OrderTicketRepository orderTicketRepository;
    private final OrderRepository orderRepository;
    private final TicketRepository ticketRepository;

    public void addOrderTicket(OrderTicket orderTicket, int orderId, int ticketId) throws NoSuchElementException {
        var order = orderRepository.getOrderById(orderId);
        var ticket = ticketRepository.getTicketById(ticketId);

        if (order.isPresent() && ticket.isPresent()) {
            orderTicket.setOrder(order.get());
            orderTicket.setTicket(ticket.get());
            orderTicketRepository.save(orderTicket);
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<OrderTicket> getAllOrderTickets() {
        return orderTicketRepository.getAllOrderTickets();
    }

    public Optional<OrderTicket> getOrderTicketById(int orderId, int ticketId) {
        return orderTicketRepository.getOrderTicketById(orderId, ticketId);
    }

    public void updateQuantityById(int quantity, int orderId, int ticketId) throws NoSuchElementException {
        var orderTicket = getOrderTicketById(orderId, ticketId);

        if (orderTicket.isPresent()) {
            orderTicketRepository.updateQuantityById(quantity, orderId, ticketId);
        } else {
            throw new NoSuchElementException();
        }
    }

    public void deleteOrderTicketById(int orderId, int ticketId) throws NoSuchElementException {
        var orderTicket = getOrderTicketById(orderId, ticketId);

        if (orderTicket.isPresent()) {
            orderTicketRepository.deleteOrderTicketById(orderId, ticketId);
        } else {
            throw new NoSuchElementException();
        }
    }
}

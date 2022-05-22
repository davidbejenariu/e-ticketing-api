package com.example.etickets.repository;

import com.example.etickets.entity.OrderTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderTicketRepository extends JpaRepository<OrderTicket, Integer> {
    @Query("select ot from OrderTicket ot")
    List<OrderTicket> getAllOrderTickets();

    @Query("select ot from OrderTicket ot " +
            "where ot.id.orderId = :orderId and ot.id.ticketId = :ticketId")
    Optional<OrderTicket> getOrderTicketById(int orderId, int ticketId);

    @Modifying
    @Query("update OrderTicket ot " +
            "set ot.quantity = :quantity " +
            "where ot.id.orderId = :orderId and ot.id.ticketId = :ticketId")
    void updateQuantityById(int quantity, int orderId, int ticketId);

    @Modifying
    @Query("delete from OrderTicket ot " +
            "where ot.id.orderId = :orderId and ot.id.ticketId = :ticketId")
    void deleteOrderTicketById(int orderId, int ticketId);
}

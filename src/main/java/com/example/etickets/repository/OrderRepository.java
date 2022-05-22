package com.example.etickets.repository;

import com.example.etickets.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("select o from Order o " +
            "where o.id = :id")
    Optional<Order> getOrderById(int id);

    @Query("select sum(ot.quantity * t.price) from Order o " +
            "join OrderTicket ot on (o.id = ot.id.orderId) " +
            "join Ticket t on (ot.id.ticketId = t.id) " +
            "where o.id = :id " +
            "group by o.id ")
    BigDecimal getOrderTotalById(int id);

    @Modifying
    @Query("update Order o " +
            "set o.orderDate = :orderDate " +
            "where o.id = :id")
    void updateDateById(Date orderDate, int id);

    @Modifying
    @Query("delete from Order o " +
            "where o.id = :id")
    void deleteOrderById(int id);
}

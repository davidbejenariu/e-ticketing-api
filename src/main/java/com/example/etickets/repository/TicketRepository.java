package com.example.etickets.repository;

import com.example.etickets.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    @Query("select t from Ticket t " +
            "where t.id = :id")
    Optional<Ticket> getTicketById(int id);

    @Modifying
    @Query("update Ticket t " +
            "set t.price = :price " +
            "where t.id = :id")
    void updatePriceById(BigDecimal price, int id);

    @Modifying
    @Query("delete from Ticket t " +
            "where t.id = :id")
    void deleteTicketById(int id);
}

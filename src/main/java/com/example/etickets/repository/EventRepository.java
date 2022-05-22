package com.example.etickets.repository;

import com.example.etickets.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query("select e from Event e " +
            "where e.type = :type")
    List<Event> getEventsByType(String type);

    @Query("select e from Event e " +
            "where e.id = :id")
    Optional<Event> getEventById(int id);

    @Query("select sum(ot.quantity) from Event e " +
            "join Ticket t on (e = t.event) " +
            "join OrderTicket ot on (t.id = ot.id.ticketId)" +
            "where e.id = :id")
    int getTicketsSoldById(int id);

    @Modifying
    @Query("update Event e " +
            "set e.startDate = :startDate " +
            "where e.id = :id")
    void updateStartDateById(Date startDate, int id);

    @Modifying
    @Query("update Event e " +
            "set e.endDate = :endDate " +
            "where e.id = :id")
    void updateEndDateById(Date endDate, int id);

    @Modifying
    @Query("delete from Event e " +
            "where e.id = :id")
    void deleteEventById(int id);
}

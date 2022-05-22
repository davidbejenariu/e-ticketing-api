package com.example.etickets.controller;

import com.example.etickets.entity.Ticket;
import com.example.etickets.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/ticket/{event-id}")
    public ResponseEntity<Void> addTicket(@RequestBody Ticket ticket, @PathVariable("event-id") int eventId) {
        try {
            ticketService.addTicket(ticket, eventId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tickets")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/ticket/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id") int id) {
        var ticket = ticketService.getTicketById(id);

        if (ticket.isPresent()) {
            return ResponseEntity.of(ticket);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/ticket/{id}")
    public ResponseEntity<Void> updatePriceById(@RequestParam BigDecimal price, @PathVariable("id") int id) {
        try {
            ticketService.updatePriceById(price, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/ticket/{id}")
    public ResponseEntity<Void> deleteTicketById(@PathVariable("id") int id) {
        try {
            ticketService.deleteTicketById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

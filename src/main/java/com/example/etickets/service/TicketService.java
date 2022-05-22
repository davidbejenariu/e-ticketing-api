package com.example.etickets.service;

import com.example.etickets.entity.Ticket;
import com.example.etickets.repository.EventRepository;
import com.example.etickets.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;

    public void addTicket(Ticket ticket, int eventId) throws NoSuchElementException {
        var event = eventRepository.getEventById(eventId);

        if (event.isPresent()) {
            ticket.setEvent(event.get());
            ticketRepository.save(ticket);
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(int id) {
        return ticketRepository.getTicketById(id);
    }

    public void updatePriceById(BigDecimal price, int id) throws NoSuchElementException {
        var ticket = getTicketById(id);

        if (ticket.isPresent()) {
            ticketRepository.updatePriceById(price, id);
        } else {
            throw new NoSuchElementException();
        }
    }

    public void deleteTicketById(int id) throws NoSuchElementException {
        var ticket = getTicketById(id);

        if (ticket.isPresent()) {
            ticketRepository.deleteTicketById(id);
        } else {
            throw new NoSuchElementException();
        }
    }
}

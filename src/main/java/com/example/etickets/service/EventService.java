package com.example.etickets.service;

import com.example.etickets.entity.Event;
import com.example.etickets.repository.EventRepository;
import com.example.etickets.repository.VenueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;

    public void addEvent(Event event, int venueId) throws NoSuchElementException {
        var venue = venueRepository.getVenueById(venueId);

        if (venue.isPresent()) {
            event.setVenue(venue.get());
            eventRepository.save(event);
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(int id) {
        return eventRepository.getEventById(id);
    }

    public List<Event> getEventsByType(String type) {
        return eventRepository.getEventsByType(type);
    }

    public int getTicketsSoldById(int id) {
        return eventRepository.getTicketsSoldById(id);
    }

    public void updateStartDateById(Date startDate, int id) throws NoSuchElementException {
        var event = getEventById(id);

        if (event.isPresent()) {
            eventRepository.updateStartDateById(startDate, id);
        } else {
            throw new NoSuchElementException();
        }
    }

    public void updateEndDateById(Date endDate, int id) throws NoSuchElementException {
        var event = getEventById(id);

        if (event.isPresent()) {
            eventRepository.updateEndDateById(endDate, id);
        } else {
            throw new NoSuchElementException();
        }
    }

    public void deleteEventById(int id) throws NoSuchElementException {
        var event = getEventById(id);

        if (event.isPresent()) {
            eventRepository.deleteEventById(id);
        } else {
            throw new NoSuchElementException();
        }
    }
}

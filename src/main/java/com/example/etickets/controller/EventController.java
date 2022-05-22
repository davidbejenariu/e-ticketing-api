package com.example.etickets.controller;

import com.example.etickets.entity.Event;
import com.example.etickets.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class EventController {
    private final EventService eventService;

    @PostMapping("/event/{address-id}")
    public ResponseEntity<Void> addEvent(@RequestBody Event event, @PathVariable("address-id") int venueId) {
        try {
            eventService.addEvent(event, venueId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/events/{type}")
    public List<Event> getEventsByType(@PathVariable("type") String type) {
        return eventService.getEventsByType(type);
    }

    @GetMapping("/event/{id}/tickets-sold")
    public int getTicketsSoldById(@PathVariable("id") int id) {
        return eventService.getTicketsSoldById(id);
    }

    @PutMapping("/event/{id}/start-date")
    public ResponseEntity<Void> updateStartDateById(@RequestParam Date startDate, @PathVariable("id") int id) {
        try {
            eventService.updateStartDateById(startDate, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/event/{id}/end-date")
    public ResponseEntity<Void> updateEndDateById(@RequestParam Date endDate, @PathVariable("id") int id) {
        try {
            eventService.updateEndDateById(endDate, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<Void> deleteEventById(@PathVariable("id") int id) {
        try {
            eventService.deleteEventById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

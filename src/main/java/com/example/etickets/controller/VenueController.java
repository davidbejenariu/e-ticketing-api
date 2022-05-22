package com.example.etickets.controller;

import com.example.etickets.entity.Venue;
import com.example.etickets.service.VenueService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class VenueController {
    private final VenueService venueService;

    @PostMapping("/venue/{address-id}")
    public ResponseEntity<Void> addVenue(@RequestBody Venue venue, @PathVariable("address-id") int addressId) {
        try {
            venueService.addVenue(venue, addressId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/venues")
    public List<Venue> getAllVenues() {
        return venueService.getAllVenues();
    }

    @GetMapping("/venues/{type}")
    public List<Venue> getVenuesByType(@PathVariable("type") String type) {
        return venueService.getVenuesByType(type);
    }

    @PutMapping("/venue/{id}")
    public ResponseEntity<Void> updateCapacityById(@RequestParam int capacity,
                                                   @PathVariable("id") int id) {
        try {
            venueService.updateCapacityById(capacity, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/venue/{id}")
    public ResponseEntity<Void> deleteVenueById(@PathVariable("id") int id) {
        try {
            venueService.deleteVenueById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

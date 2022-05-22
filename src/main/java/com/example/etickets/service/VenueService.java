package com.example.etickets.service;

import com.example.etickets.entity.Venue;
import com.example.etickets.repository.AddressRepository;
import com.example.etickets.repository.VenueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class VenueService {
    private final VenueRepository venueRepository;
    private final AddressRepository addressRepository;

    public void addVenue(Venue venue, int addressId) throws NoSuchElementException {
        var address = addressRepository.getAddressById(addressId);

        if (address.isPresent()) {
            venue.setAddress(address.get());
            venueRepository.save(venue);
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    public Optional<Venue> getVenueById(int id) {
        return venueRepository.getVenueById(id);
    }

    public List<Venue> getVenuesByType(String type) {
        return venueRepository.getVenuesByType(type);
    }

    public void updateCapacityById(int capacity, int id) throws NoSuchElementException {
        var venue = getVenueById(id);

        if (venue.isPresent()) {
            venueRepository.updateCapacityById(capacity, id);
        } else {
            throw new NoSuchElementException();
        }
    }

    public void deleteVenueById(int id) throws NoSuchElementException {
        var venue = getVenueById(id);

        if (venue.isPresent()) {
            venueRepository.deleteVenueById(id);
        } else {
            throw new NoSuchElementException();
        }
    }
}

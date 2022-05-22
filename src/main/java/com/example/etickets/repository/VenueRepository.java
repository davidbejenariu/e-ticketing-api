package com.example.etickets.repository;

import com.example.etickets.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VenueRepository extends JpaRepository<Venue, Integer> {
    @Query("select v from Venue v " +
            "where v.id = :id")
    Optional<Venue> getVenueById(int id);

    @Query("select v from Venue v " +
            "where v.type = :type")
    List<Venue> getVenuesByType(String type);

    @Modifying
    @Query("update Venue v " +
            "set v.capacity = :capacity " +
            "where v.id = :id")
    void updateCapacityById(int capacity, int id);

    @Modifying
    @Query("delete from Venue v " +
            "where v.id = :id")
    void deleteVenueById(int id);
}

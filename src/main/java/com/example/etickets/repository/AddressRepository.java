package com.example.etickets.repository;

import com.example.etickets.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query("select a from Address a where a.id = :id")
    Optional<Address> getAddressById(int id);

    @Query("select a from Address a where a.zipCode = :zipCode")
    List<Address> getAddressesByZipCode(String zipCode);

    @Modifying
    @Query("update Address a " +
            "set a.street = :street, a.number = :number " +
            "where a.id = :id")
    void updateStreetAndNumberById(String street, int number, int id);

    @Modifying
    @Query("delete from Address a where a.id = :id")
    void deleteAddressById(int id);
}

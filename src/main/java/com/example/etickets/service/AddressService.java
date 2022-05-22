package com.example.etickets.service;

import com.example.etickets.entity.Address;
import com.example.etickets.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public void addAddress(Address address) {
        addressRepository.save(address);
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Optional<Address> getAddressById(int id) {
        return addressRepository.getAddressById(id);
    }

    public List<Address> getAddressesByZipCode(String zipCode) {
        return addressRepository.getAddressesByZipCode(zipCode);
    }

    public void updateStreetAndNumberById(String street, int number, int id) throws NoSuchElementException {
        var address = getAddressById(id);

        if (address.isPresent()) {
            addressRepository.updateStreetAndNumberById(street, number, id);
        } else {
            throw new NoSuchElementException();
        }
    }

    public void deleteAddressById(int id) throws NoSuchElementException {
        var address = getAddressById(id);

        if (address.isPresent()) {
            addressRepository.deleteAddressById(id);
        } else {
            throw new NoSuchElementException();
        }
    }
}

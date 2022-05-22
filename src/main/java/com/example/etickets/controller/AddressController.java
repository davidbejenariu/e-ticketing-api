package com.example.etickets.controller;

import com.example.etickets.entity.Address;
import com.example.etickets.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AddressController {
    private final AddressService addressService;

    @PostMapping("/address")
    public void addAddress(@RequestBody Address address) {
        addressService.addAddress(address);
    }

    @GetMapping("/addresses")
    public List<Address> getAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/address/id/{id}")
    public ResponseEntity<Address> getAddressesById(@PathVariable("id") int id) {
        var address = addressService.getAddressById(id);

        if (address.isPresent()) {
            return ResponseEntity.of(address);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/address/{zip-code}")
    public List<Address> getAddressesByZipCode(@PathVariable("zip-code") String zipCode) {
        return addressService.getAddressesByZipCode(zipCode);
    }

    @PutMapping("/address/{id}")
    public ResponseEntity<Void> updateStreetAndNumberById(@RequestParam String street,
                                                          @RequestParam int number,
                                                          @PathVariable("id") int id) {
        try {
            addressService.updateStreetAndNumberById(street, number, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<Void> deleteAddressById(@PathVariable("id") int id) {
        try {
            addressService.deleteAddressById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

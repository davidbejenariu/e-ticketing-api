package com.example.etickets.controller;

import com.example.etickets.entity.Customer;
import com.example.etickets.entity.Order;
import com.example.etickets.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/customer/{address-id}")
    public ResponseEntity<Void> addCustomer(@RequestBody Customer customer, @PathVariable("address-id") int addressId) {
        try {
            customerService.addCustomer(customer, addressId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id) {
        var customer = customerService.getCustomerById(id);

        if (customer.isPresent()) {
            return ResponseEntity.of(customer);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/customer")
    public ResponseEntity<Customer> getCustomerByEmail(@RequestParam String email) {
        var customer = customerService.getCustomerByEmail(email);

        if (customer.isPresent()) {
            return ResponseEntity.of(customer);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/customer/{id}/orders")
    public List<Order> getOrdersById(@PathVariable int id) {
        return customerService.getOrdersById(id);
    }

    @PutMapping("/customer/{id}/full-name")
    public ResponseEntity<Void> updateFullNameById(@RequestParam String firstName,
                                                   @RequestParam String lastName,
                                                   @PathVariable("id") int id) {
        try {
            customerService.updateFullNameById(firstName, lastName, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/customer/{id}/email")
    public ResponseEntity<Void> updateEmailById(@RequestParam String email,
                                                @PathVariable("id") int id) {
        try {
            customerService.updateEmailById(email, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/customer/{id}/phone")
    public ResponseEntity<Void> updatePhoneById(@RequestParam String phone,
                                                @PathVariable("id") int id) {
        try {
            customerService.updatePhoneById(phone, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable("id") int id) {
        try {
            customerService.deleteCustomerById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

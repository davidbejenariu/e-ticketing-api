package com.example.etickets.service;

import com.example.etickets.entity.Customer;
import com.example.etickets.entity.Order;
import com.example.etickets.repository.AddressRepository;
import com.example.etickets.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public void addCustomer(Customer customer, int addressId) throws NoSuchElementException {
        var address = addressRepository.getAddressById(addressId);

        if (address.isPresent()) {
            customer.setAddress(address.get());
            customerRepository.save(customer);
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(int id) {
        return customerRepository.getCustomerById(id);
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.getCustomerByEmail(email);
    }

    public List<Order> getOrdersById(int id) {
        return customerRepository.getOrdersById(id);
    }

    public void updateFullNameById(String firstName, String lastName, int id) throws NoSuchElementException {
        var customer = getCustomerById(id);

        if (customer.isPresent()) {
            customerRepository.updateFullNameById(firstName, lastName, id);
        } else {
            throw new NoSuchElementException();
        }
    }

    public void updateEmailById(String email, int id) throws NoSuchElementException {
        var customer = getCustomerById(id);

        if (customer.isPresent()) {
            customerRepository.updateEmailById(email, id);
        } else {
            throw new NoSuchElementException();
        }
    }

    public void updatePhoneById(String phone, int id) throws NoSuchElementException {
        var customer = getCustomerById(id);

        if (customer.isPresent()) {
            customerRepository.updatePhoneById(phone, id);
        } else {
            throw new NoSuchElementException();
        }
    }

    public void deleteCustomerById(int id) throws NoSuchElementException {
        var customer = getCustomerById(id);

        if (customer.isPresent()) {
            customerRepository.deleteCustomerById(id);
        } else {
            throw new NoSuchElementException();
        }
    }
}

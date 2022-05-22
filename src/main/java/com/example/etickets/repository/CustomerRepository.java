package com.example.etickets.repository;

import com.example.etickets.entity.Customer;
import com.example.etickets.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("select c from Customer c where c.id = :id")
    Optional<Customer> getCustomerById(int id);

    @Query("select c from Customer c where c.email = :email")
    Optional<Customer> getCustomerByEmail(String email);

    @Query("select o from Customer c " +
            "join Order o on (c = o.customer) " +
            "where c.id = :id")
    List<Order> getOrdersById(int id);

    @Modifying
    @Query("update Customer c " +
            "set c.firstName = :firstName, c.lastName = :lastName " +
            "where c.id = :id")
    void updateFullNameById(String firstName, String lastName, int id);

    @Modifying
    @Query("update Customer c " +
            "set c.email = :email " +
            "where c.id = :id")
    void updateEmailById(String email, int id);

    @Modifying
    @Query("update Customer c " +
            "set c.phone = :phone " +
            "where c.id = :id")
    void updatePhoneById(String phone, int id);

    @Modifying
    @Query("delete from Customer c where c.id = :id")
    void deleteCustomerById(int id);
}

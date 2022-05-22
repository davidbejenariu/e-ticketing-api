package com.example.etickets.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "address")
public class Address extends GenericEntity {
    private String street;
    private int number;
    private String zipCode;
    private String town;
    private String country;

//    @OneToMany(mappedBy = "address")
//    private Set<Customer> customers;
//
//    @OneToMany(mappedBy = "address")
//    private Set<Venue> venues;
}

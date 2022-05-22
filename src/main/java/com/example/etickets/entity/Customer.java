package com.example.etickets.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "customer")
public class Customer extends GenericEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String type;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "address_id")
    private Address address;

//    @OneToMany(mappedBy = "customer")
//    private Set<Order> orders;
}

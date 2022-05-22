package com.example.etickets.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "venue")
public class Venue extends GenericEntity {
    private String name;
    private String type;
    private int capacity;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "address_id")
    private Address address;

//    @OneToMany(mappedBy = "venue")
//    private Set<Event> events;
}

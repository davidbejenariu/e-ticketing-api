package com.example.etickets.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "event")
public class Event extends GenericEntity {
    private String Name;
    private String type;
    private Date startDate;
    private Date endDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "venue_id")
    private Venue venue;

//    @OneToMany(mappedBy = "event")
//    private Set<Ticket> tickets;
}

package com.example.etickets.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "ticket")
public class Ticket extends GenericEntity {
    private String description;
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "event_id")
    private Event event;

//    @OneToMany(mappedBy = "ticket")
//    private Set<OrderTicket> orderTickets;
}

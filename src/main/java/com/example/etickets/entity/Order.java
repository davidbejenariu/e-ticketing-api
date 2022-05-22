package com.example.etickets.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "order0")
public class Order extends GenericEntity {
    private Date orderDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

//    @OneToMany(mappedBy = "order")
//    private Set<OrderTicket> orderTickets;
}

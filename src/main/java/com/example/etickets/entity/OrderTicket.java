package com.example.etickets.entity;

import com.example.etickets.OrderTicketId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "order_ticket")
public class OrderTicket {
    @EmbeddedId
    private OrderTicketId id = new OrderTicketId();

    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("ticketId")
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public OrderTicket(int orderId, int ticketId, int quantity) {
        this.id = new OrderTicketId();
        this.id.setOrderId(orderId);
        this.id.setTicketId(ticketId);
        this.quantity = quantity;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//
//        if (obj == null || getClass() != obj.getClass()) {
//            return false;
//        }
//
//        OrderTicket that = (OrderTicket) obj;
//
//        return Objects.equals(order, that.order) &&
//                Objects.equals(ticket, that.ticket);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(order, ticket);
//    }
}

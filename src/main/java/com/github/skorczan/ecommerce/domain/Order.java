package com.github.skorczan.ecommerce.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user")
    private User user;

    private Address userAddress;

    private Address deliveryAddress;

    private LocalDateTime orderTime;

    private double totalCost;

    @Enumerated(EnumType.STRING)
    private State state;

    @ElementCollection
    private List<Entry> entries;

    public enum State {
        NEW,
        IN_DELIVERY,
        DELIVERED
    }

    @Embeddable
    @Data
    public static class Entry {

        @ManyToOne(optional = false)
        @JoinColumn(name = "product")
        private Product product;

        @Column(name = "price")
        private double price;

        @Column(name = "count")
        private double count;
    }
}

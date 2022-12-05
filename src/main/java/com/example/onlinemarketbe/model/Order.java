package com.example.onlinemarketbe.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import java.util.HashSet;
import java.util.Set;



@Getter
@Setter
@Entity


@Table(name = "orders")


public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @NotNull
    private int totalPrice;
    @ManyToOne
    @JoinColumn(name = "payment_id")
    Payment payment;
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    User buyer;
    @Column
    @NotNull
    private Date dateOrder;
    @ManyToOne
    @JoinColumn(name = "status_id")
    Status status;
    @OneToMany(fetch = FetchType.EAGER)
//    @JoinTable( name = "order_item",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> items = new HashSet<>();

}

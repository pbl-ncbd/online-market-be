package com.example.onlinemarketbe.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @NotNull
    private double totalPrice;
    @Column
    @NotNull
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "product_id")
    Product product;
    @OneToOne
    @JoinColumn(name = "type_id")
    Type type;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}

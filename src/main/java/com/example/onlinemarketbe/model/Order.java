package com.example.onlinemarketbe.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;


@Getter
@Setter
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @NotNull
    private double totalPrice;
    @ManyToOne
    @JoinColumn(name = "payment_id")
    Payment payment;
    @ManyToOne
    @JoinColumn(name = "user_id_buyer")
    User userBuyer;
    @ManyToOne
    @JoinColumn(name = "user_id_saler")
    User userSaler;
    @Column
    @NotNull
    private Date dateOrder;
    @ManyToOne
    @JoinColumn(name = "status_id")
    Status status;



}

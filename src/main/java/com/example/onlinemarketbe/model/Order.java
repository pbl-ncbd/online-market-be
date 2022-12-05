package com.example.onlinemarketbe.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
<<<<<<< HEAD
import java.util.HashSet;
import java.util.Set;
=======
>>>>>>> a4af00c (create model)


@Getter
@Setter
@Entity
<<<<<<< HEAD
@Table(name = "orders")
=======
>>>>>>> a4af00c (create model)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @NotNull
<<<<<<< HEAD
    private int totalPrice;
=======
    private double totalPrice;
>>>>>>> a4af00c (create model)
    @ManyToOne
    @JoinColumn(name = "payment_id")
    Payment payment;
    @ManyToOne
<<<<<<< HEAD
    @JoinColumn(name = "buyer_id")
    User buyer;
=======
    @JoinColumn(name = "user_id_buyer")
    User userBuyer;
    @ManyToOne
    @JoinColumn(name = "user_id_saler")
    User userSaler;
>>>>>>> a4af00c (create model)
    @Column
    @NotNull
    private Date dateOrder;
    @ManyToOne
    @JoinColumn(name = "status_id")
    Status status;


<<<<<<< HEAD
    @OneToMany(fetch = FetchType.EAGER)
//    @JoinTable( name = "order_item",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> items = new HashSet<>();
=======

>>>>>>> a4af00c (create model)
}

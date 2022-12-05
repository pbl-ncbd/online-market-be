package com.example.onlinemarketbe.model;
import lombok.Getter;
<<<<<<< HEAD
import lombok.NoArgsConstructor;
=======
>>>>>>> a4af00c (create model)
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
<<<<<<< HEAD
@Table(name = "product")
@NoArgsConstructor
=======
>>>>>>> a4af00c (create model)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "category_id")
<<<<<<< HEAD
    Category category;
=======
    Categoty categoty;
>>>>>>> a4af00c (create model)
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @Column
    @NotNull
    @Size(max = 255)
    private String name;
    @Column
    @NotNull
    private double price;
    @Column
    @NotNull
    @Size(max = 255)
<<<<<<< HEAD
    private String description;
    @Column
    @NotNull
    private int quantity;
=======
    private String describe;
    @Column
    @NotNull
    private int number;
>>>>>>> a4af00c (create model)
    @Column
    @NotNull
    private boolean status;


<<<<<<< HEAD

=======
>>>>>>> a4af00c (create model)
}

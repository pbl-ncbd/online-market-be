package com.example.onlinemarketbe.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "product")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

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
    private String description;
    @Column
    @NotNull
    private int quantity;
    @Column
    @NotNull
    private boolean status;



}

package com.example.onlinemarketbe.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
    @Column
    @Size(max = 255)
    private String name;
    @Column
    @Size(max = 255)
    private String size;
    @Column
    @Size(max = 255)
    private String color;
    @Column
    @NotNull
    private  boolean status;

}

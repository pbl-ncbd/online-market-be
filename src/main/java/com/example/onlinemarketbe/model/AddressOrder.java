package com.example.onlinemarketbe.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class AddressOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @NotNull
    @Size(max = 255)
    private String province_city;
    @Column
    @NotNull
    @Size(max = 255)
    private String address;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

}

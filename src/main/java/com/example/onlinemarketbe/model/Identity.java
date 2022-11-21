package com.example.onlinemarketbe.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Identity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @NotNull
    @Size(max = 255)
    private String idCart;
    @Column
    @NotNull
    @Size(max = 255)
    private String name;
    @Column
    @NotNull
    @Size(max = 255)
    private String gender;
    @Column
    @NotNull
    @Size(max = 255)
    private String address;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

}

package com.example.onlinemarketbe.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @NotNull
    @Size(max = 255)
    private String username;
    @Column
    @NotNull
    @Size(max = 255)
    private String password;
    @ManyToOne
    @JoinColumn(name = "info_id")
    Information information;
    @Column
    @NotNull
    private boolean status;
}

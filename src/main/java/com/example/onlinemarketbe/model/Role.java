package com.example.onlinemarketbe.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
<<<<<<< HEAD

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
=======
    @Column
    @NotNull
    @Size(max = 255)
    private String name;
>>>>>>> a4af00c (create model)

}

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
public class Information {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @NotNull
    @Size(max = 255)
    private String name;
    @Column
    @NotNull
    @Size(max = 255)
    private String phone;
    @Column
    @NotNull
    private Date birthDate;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    @Column
    @NotNull
    private String address;



}

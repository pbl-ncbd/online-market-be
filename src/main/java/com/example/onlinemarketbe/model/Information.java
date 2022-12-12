package com.example.onlinemarketbe.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Information {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @Size(max = 255)
    private String name;
    @Column
    @Size(max = 255)
    private String phone;
    @Column
    private Date birthDate;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    @Column
    private String address;

}

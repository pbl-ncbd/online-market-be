package com.example.onlinemarketbe.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @Size(max = 255)
    private String district_name;
    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;
}

package com.example.onlinemarketbe.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
@Setter
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @NotNull
    @Size(max = 255)
    private String season;
    @ManyToOne
    @JoinColumn(name = "user_id_reported")
    User reported;
    @ManyToOne
    @JoinColumn(name = "user_id_report")
    User report;
    @Column
    @NotNull
    private boolean status;


}

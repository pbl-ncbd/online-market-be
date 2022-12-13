package com.example.onlinemarketbe.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class IdentityImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @NotNull
    private String url;
    @ManyToOne
    @JoinColumn(name = "identity_id", nullable = false)
    private Identity identity;
}

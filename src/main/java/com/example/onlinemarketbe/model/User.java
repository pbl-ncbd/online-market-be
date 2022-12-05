package com.example.onlinemarketbe.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity

@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})

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

    private boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


}

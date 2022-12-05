package com.example.onlinemarketbe.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
<<<<<<< HEAD
import java.util.HashSet;
import java.util.Set;
=======
>>>>>>> a4af00c (create model)

@Getter
@Setter
@Entity
<<<<<<< HEAD
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
=======
>>>>>>> a4af00c (create model)
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
<<<<<<< HEAD

    @Column
    @NotNull
    private boolean active;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
=======
    @Column
    @NotNull
    private boolean status;
>>>>>>> a4af00c (create model)
}

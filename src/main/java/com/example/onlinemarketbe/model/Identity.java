package com.example.onlinemarketbe.model;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
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
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "identity", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<IdentityImage> idCards = new ArrayList<>();
    @Column
    private boolean confirm ;
    @Column
    private boolean deleted ;

}

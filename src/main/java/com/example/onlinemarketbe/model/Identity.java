package com.example.onlinemarketbe.model;
import lombok.Getter;
import lombok.Setter;
<<<<<<< HEAD
=======

>>>>>>> a4af00c (create model)
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Identity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
<<<<<<< HEAD

    @Column
    @NotNull
    @Size(max = 255)
    private String idCard;

=======
    @Column
    @NotNull
    @Size(max = 255)
    private String idCart;
>>>>>>> a4af00c (create model)
    @Column
    @NotNull
    @Size(max = 255)
    private String name;
<<<<<<< HEAD

    @Column
    @NotNull
    private String gender;

=======
    @Column
    @NotNull
    @Size(max = 255)
    private String gender;
>>>>>>> a4af00c (create model)
    @Column
    @NotNull
    @Size(max = 255)
    private String address;
<<<<<<< HEAD

    @Column
    @Size(max = 255)
    private String cardImage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private boolean confirmed;

=======
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
>>>>>>> a4af00c (create model)

}

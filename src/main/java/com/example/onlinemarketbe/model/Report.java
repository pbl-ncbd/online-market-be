package com.example.onlinemarketbe.model;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @NotNull
    @Size(max = 255)
    private String reason;
    @ManyToOne
    @JoinColumn(name = "user_id_reported")
    User reported;
    @ManyToOne
    @JoinColumn(name = "user_id_report")
    User report;
    @Column
    @NotNull
    private boolean status = false;

    @Column(name="create_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createAt;
}

package com.example.onlinemarketbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;

/**
 * A DTO for the {@link com.example.onlinemarketbe.model.User} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private int id;
    @NotNull
    @Size(max = 255)
    private String informationName;
    @NotNull
    @Size(max = 255)
    private String informationPhone;
}
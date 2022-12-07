package com.example.onlinemarketbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link com.example.onlinemarketbe.model.Identity} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerIdentityDto implements Serializable {
    private int id;
    @NotNull
    @Size(max = 255)
    private String name;
    @NotNull
    @Size(max = 255)
    private String gender;
    @NotNull
    @Size(max = 255)
    private String address;
    private int userId;
    private List<String> idCardUrls;
}
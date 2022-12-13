package com.example.onlinemarketbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link com.example.onlinemarketbe.model.Identity} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdentityDto implements Serializable {
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
    private UserDto user;
    private List<IdentityImageDto> idCards = new ArrayList<>();
    private boolean confirm = false;

    /**
     * A DTO for the {@link com.example.onlinemarketbe.model.User} entity
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserDto implements Serializable {
        private int id;
        @NotNull
        private boolean active;
    }

    /**
     * A DTO for the {@link com.example.onlinemarketbe.model.IdentityImage} entity
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IdentityImageDto implements Serializable {
        private int id;
        @NotNull
        private String url;
    }
}
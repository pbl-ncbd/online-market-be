package com.example.onlinemarketbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link com.example.onlinemarketbe.model.Report} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto implements Serializable {
    private int id;
    @NotNull
    @Size(max = 255)
    private String reason;
    private UserDto reported;
    private UserDto report;
    @NotNull
    private boolean status = false;
}
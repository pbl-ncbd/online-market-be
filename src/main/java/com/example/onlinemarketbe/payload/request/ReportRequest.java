package com.example.onlinemarketbe.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequest {
    @NotNull
    private int reportId;
    @NotNull
    private int reportedId;
    @NotNull
    @Size(max = 255)
    private String reason;
}

package com.example.onlinemarketbe.payload.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ReportRequest {
    @NotNull
    private int reportId;
    @NotNull
    private int reportedId;
    @NotNull
    @Size(max = 255)
    private String reason;
}

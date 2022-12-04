package com.example.onlinemarketbe.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class ListTypeRequest {
    private String name;
    private String size;
    private String color;


}

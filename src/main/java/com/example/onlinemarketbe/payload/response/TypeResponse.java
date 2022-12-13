package com.example.onlinemarketbe.payload.response;

import com.example.onlinemarketbe.model.Product;
import com.example.onlinemarketbe.model.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter

@NoArgsConstructor
public class TypeResponse {

    private int id;

    private String name;

    private String size;

    private String color;


    public TypeResponse(Type type) {
        if(type != null) {
            this.id = type.getId();
            this.name = type.getName();
            this.size = type.getSize();
            this.color = type.getColor();
        }

    }
}

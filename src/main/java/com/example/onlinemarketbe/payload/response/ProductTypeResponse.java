package com.example.onlinemarketbe.payload.response;

import com.example.onlinemarketbe.model.Product;
import com.example.onlinemarketbe.model.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class ProductTypeResponse {
    private Product product;
    private List<Type>  typeList;
}

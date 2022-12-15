package com.example.onlinemarketbe.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
public class InfoProduct {
    private String nameProduct;
    private String typeOrder;
    private String nameShop;
    private int numberProduct;
    private double price;
    private String url;

}

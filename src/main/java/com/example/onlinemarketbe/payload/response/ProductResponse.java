package com.example.onlinemarketbe.payload.response;
import com.example.onlinemarketbe.model.Category;
import com.example.onlinemarketbe.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter

@NoArgsConstructor
public class ProductResponse {

    private int id;

    private String name;

    private double price;
    private int quantity;
private  String nameShop;
    private String description;

    private boolean status;
    Category category;
    public ProductResponse(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.quantity=product.getQuantity();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.status = product.isStatus();
        this.category = product.getCategory();
        this.quantity=product.getQuantity();
        this.nameShop= product.getUser().getUsername();
    }

}

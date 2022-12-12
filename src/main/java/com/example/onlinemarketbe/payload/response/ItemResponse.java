package com.example.onlinemarketbe.payload.response;

import com.example.onlinemarketbe.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter

@NoArgsConstructor
public class ItemResponse {
    private Integer id;
    private int userId;
    private double totalPrice;

    private int quantity;

    ProductResponse product;

    TypeResponse type;

    public ItemResponse(Item item){
        this.id = item.getId();
        this.userId = item.getUser().getId();
        this.totalPrice = item.getTotalPrice();
        this.quantity = item.getQuantity();
        this.product = new ProductResponse(item.getProduct());
        if(item.getType()!= null) {
            this.type = new TypeResponse(item.getType());
        }
        else
        {
            this.type=null;
        }
    }

}

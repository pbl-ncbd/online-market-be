package com.example.onlinemarketbe.payload.response;

import com.example.onlinemarketbe.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


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

    private List<UrlImgResponse> urlImgList;

    public ItemResponse(Item item, List<UrlImgResponse> urlImgList){
        this.id = item.getId();
        this.userId = item.getUser().getId();
        this.totalPrice = item.getTotalPrice();
        this.quantity = item.getQuantity();
        this.product = new ProductResponse(item.getProduct());
        if(item.getType() == null){
            this.type = null;
        } else
        this.type = new TypeResponse(item.getType());
        this.urlImgList = urlImgList;

    }

}

package com.example.onlinemarketbe.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ViewOrder {
    private Date dateOrder;
    private String addressOrder;
    private String namePersonOrder;
    private double totalPrice;
    private String payment;
    private List<InfoProduct> products;





}

package com.example.onlinemarketbe.payload.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class CreateOrderRequest {
    private int idPayment;
    private List<Integer> listIdItem;
    private String province;
    private String address;

}

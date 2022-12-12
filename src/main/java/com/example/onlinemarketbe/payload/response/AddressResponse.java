package com.example.onlinemarketbe.payload.response;

import com.example.onlinemarketbe.model.District;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter


public class AddressResponse {
    private String address;
    private District district;

    public AddressResponse(String address, District district) {
        this.address = address;
        this.district = district;
    }
}

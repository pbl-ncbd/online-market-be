package com.example.onlinemarketbe.payload.response;

import com.example.onlinemarketbe.model.Product;
import com.example.onlinemarketbe.model.Type;
import com.example.onlinemarketbe.model.UrlImg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class ProductTypeResponse {
    private ProductResponse productResponse;
    private List<TypeResponse>  typeList;
    private List<UrlImgResponse> urlImgList;
}

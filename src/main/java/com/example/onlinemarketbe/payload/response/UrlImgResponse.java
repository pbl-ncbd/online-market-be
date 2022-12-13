package com.example.onlinemarketbe.payload.response;

import com.example.onlinemarketbe.model.Type;
import com.example.onlinemarketbe.model.UrlImg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
public class UrlImgResponse {
    private int id;
    private String url;
    public UrlImgResponse(UrlImg urlImg) {
        this.id = urlImg.getId();
        this.url=urlImg.getUrl();

    }
}

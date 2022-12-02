package com.example.onlinemarketbe.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignupSellerRequest {
    @NotNull(message = "This is name of the seller")
    @Schema(title = "name : this fullname must be more than 5 char", example = "Nguyen Van A")
    @Size(min = 5, max = 50, message = "this name must be more than 5 char")
    private String name;

    @NotNull(message = "This is gender of the seller")
    @Schema(title = "gender : this gender of the seller ", example = "Nam")
    private String gender;

    @NotNull(message = "This is address of the seller")
    @Schema(title = "address : this address must be more than 20 char", example = "quan Lien Chieu, thanh pho Da Nang")
    @Size(min = 20, max = 255, message = "this name must e more than 5 char")
    private String address;

    @NotNull
    private String id_card;

    @NotNull
    private MultipartFile imageCard;




}

package com.example.onlinemarketbe.payload.request;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.File;

public class SignupSellerRequest {
    @NotNull(message = "This is name of the seller")
    @ApiModelProperty(notes = "name, this fullname must be more than 5 char", example = "Nguyen Van A", required = true)
    @Size(min = 5, max = 50, message = "this name must be more than 5 char")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public MultipartFile getImageCard() {
        return imageCard;
    }

    public void setImageCard(MultipartFile imageCard) {
        this.imageCard = imageCard;
    }

    @NotNull(message = "This is gender of the seller")
    @ApiModelProperty(notes = "gender, this gender of the seller ", example = "Nam", required = true)
    private String gender;

    @NotNull(message = "This is address of the seller")
    @ApiModelProperty(notes = "address, this address must be more than 20 char", example = "quan Lien Chieu, thanh pho Da Nang", required = true)
    @Size(min = 20, max = 255, message = "this name must e more than 5 char")
    private String address;

    @NotNull
    private String id_card;

    @NotNull
    private MultipartFile imageCard;
}

package com.example.onlinemarketbe.payload.request;

import com.example.onlinemarketbe.common.annotations.ValidGender;
import com.example.onlinemarketbe.model.Address;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class SignupSellerRequest implements Serializable {
    @NotNull
    private int userId;

    @NotNull(message = "This is name of the seller")
//    @ApiModelProperty(notes = "name, this fullname must be more than 5 char", example = "Nguyen Van A", required = true)
    @Size(min = 5, max = 255, message = "this name must be more than 5 char")
    private String name;

    @NotNull(message = "This is gender of the seller")
//    @ApiModelProperty(notes = "gender, this gender of the seller ", example = "Nam", required = true)
    @ValidGender
    private String gender;

    @NotNull(message = "This is address of the seller")
//    @ApiModelProperty(notes = "address, this address must be more than 20 char", example = "quan Lien Chieu, thanh pho Da Nang", required = true)
    @Size(min = 20, max = 255, message = "this name must e more than 5 char")
    private String address;

    @NotNull
    private MultipartFile[] idCards;
}

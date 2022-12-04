package com.example.onlinemarketbe.payload.request;

import com.example.onlinemarketbe.model.District;
import com.example.onlinemarketbe.model.Province;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class InformationRequest {

    @NotNull(message = "This is name of user")
    @Schema(title = "name", example = "Nguyen Van A")
    private String name;

    @NotNull(message = "This is phone number of user")
    @Schema(title = "phone", example = "0365999855")
    private String phone;

    @NotNull(message = "This is birth date of user")
    @Schema(title = "birthDate")
    private Date birthDate;

    @NotNull(message = "This is address of user")
    @Schema(title = "phone", example = "0365999855")
    private String address;

    @NotNull
    @Schema(title = "district")
    private int district_id;

    public int getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(int district_id) {
        this.district_id = district_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    @NotNull
    @Schema(title = "district")
    private District district;

}

package com.example.onlinemarketbe.services;

import com.example.onlinemarketbe.dto.IdentityDto;
import com.example.onlinemarketbe.dto.ReportDto;
import com.example.onlinemarketbe.dto.SellerIdentityDto;
import com.example.onlinemarketbe.dto.SellerRegisterDto;
import com.example.onlinemarketbe.payload.request.ReportRequest;

import java.util.List;

public interface SellerService {
    IdentityDto getSellerIdentity(int user_id);
    void createSeller(SellerRegisterDto sellerRegisterDto);

    void confirmSeller(int sellerId);

    void reportSeller(int sellerId, ReportRequest reportRequest);


    void deleteSeller(int sellerId);

    List<ReportDto> getSellerReports(int page);

    List<IdentityDto> getAllSellers();
}

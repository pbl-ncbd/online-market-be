package com.example.onlinemarketbe.controllers;

import com.example.onlinemarketbe.dto.ReportDto;
import com.example.onlinemarketbe.payload.request.ReportRequest;
import com.example.onlinemarketbe.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    private final SellerService sellerService;

    @Autowired
    public ReportController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("")
    public ResponseEntity<List<ReportDto>> getAllReports(@RequestParam(value = "page", required = false, defaultValue = "0") int page){
        List<ReportDto> reportDtoList = sellerService.getSellerReports(page);
        return new ResponseEntity<>(reportDtoList, HttpStatus.OK);
    }

    @PostMapping("/{id}/seller")
    public ResponseEntity<?> reportSeller(@PathVariable("id") int sellerId, @RequestBody @Valid ReportRequest reportRequest){
        sellerService.reportSeller(sellerId, reportRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

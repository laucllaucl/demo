package com.example.demo.controller;

import com.example.demo.services.ReceiptGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ReceiptGeneratorController {

    @Autowired
    private ReceiptGeneratorService receiptGeneratorService;

    @PostMapping(path = "/generate")
    public ResponseEntity<String> generateReceipt(@RequestBody MultipartFile file) throws IOException {
        return receiptGeneratorService.generateReceipt(file);
    }
}

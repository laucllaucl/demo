package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class ReceiptGeneratorServiceImpl implements ReceiptGeneratorService {

    @Autowired
    private PrinterService printerService;

    @Override
    public ResponseEntity<String> generateReceipt(MultipartFile file) throws IOException {
        try {
            InputStream inputStream = file.getInputStream();
            new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .forEach(this::handleLine);
        } catch (Exception e) {
            System.out.println("Upload Fail");
            return ResponseEntity.badRequest().body("Upload Fail");
        }
        return ResponseEntity.ok().body("Upload Success");
    }

    private void handleLine(String input) {
        printerService.printReceipt(input);
    }
}

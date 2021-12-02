package com.example.demo.services;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ReceiptGeneratorService {

    public ResponseEntity<String> generateReceipt(MultipartFile file) throws IOException;
}

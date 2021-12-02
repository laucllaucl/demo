package com.example.demo.services;

import com.example.demo.Item;
import com.example.demo.Location;
import org.springframework.stereotype.Component;

import java.util.List;

public interface PrinterService {

    public void printReceipt(String input);
}

package com.example.demo.services;

import com.example.demo.Item;
import com.example.demo.Location;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

public interface CalculatorService {

    public BigDecimal getSubTotal(List<Item> items);

    public BigDecimal getTax(List<Item> items, Location location);

    public BigDecimal getTotal(BigDecimal subTotal, BigDecimal tax);
}

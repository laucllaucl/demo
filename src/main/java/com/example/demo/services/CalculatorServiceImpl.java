package com.example.demo.services;

import com.example.demo.Item;
import com.example.demo.Location;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CalculatorServiceImpl implements CalculatorService{
    @Override
    public BigDecimal getSubTotal(@NotNull List<Item> items) {
        return items.stream()
                .reduce(
                        BigDecimal.ZERO,
                        (subTotal, item) -> subTotal.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))),
                        BigDecimal::add
                );
    }

    @Override
    public BigDecimal getTax(List<Item> items, @NotNull Location location) {
        BigDecimal tax = BigDecimal.ZERO;
        BigDecimal taxRate = location.getTaxRate();
        for (Item item : items) {
            if (location.getTaxFreeCategoryList().contains(item.getCategory())) {
                continue;
            } else {
                tax = tax.add(taxRate.multiply(BigDecimal.valueOf(item.getQuantity())).multiply(item.getPrice()));
            }
        }
        tax = BigDecimal.ZERO.equals(tax) ? BigDecimal.ZERO : tax.multiply(BigDecimal.valueOf(20)).setScale(0, RoundingMode.UP).divide(BigDecimal.valueOf(20));
        return tax;
    }

    @Override
    public BigDecimal getTotal(@NotNull BigDecimal subTotal, @NotNull BigDecimal tax) {
        return subTotal.add(tax);
    }
}

package com.example.demo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Location {
    CA(new BigDecimal("0.0975"), Arrays.asList(Category.FOOD)),
    NY(new BigDecimal("0.08875"), Arrays.asList(Category.FOOD, Category.CLOTHING)),

    ;

    private BigDecimal taxRate;
    private List<Category> taxFreeCategoryList;

    public BigDecimal getTaxRate() {
        return this.taxRate;
    }

    public List<Category> getTaxFreeCategoryList() {
        return this.taxFreeCategoryList;
    }

}

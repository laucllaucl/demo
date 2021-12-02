package com.example.demo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Category {
    FOOD(Arrays.asList("potato chips")),
    CLOTHING(Arrays.asList("shirt")),
    OTHERS(Arrays.asList("book", "pencils"))
    ;

    private List<String> products;

    public static Category getCategoryByProductName(String productName) {
        for (Category category : Category.values()) {
            if (category.products.contains(productName)) {
                return category;
            }
        }

        return OTHERS;
    }
}

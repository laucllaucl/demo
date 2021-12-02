package com.example.demo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Item {

    @NotNull
    private String name;

    @NotNull
    private Category category;

    @NotNull
    private BigDecimal price;

    private int quantity;

    public Category getCategory() {
        return Objects.requireNonNullElseGet(this.category, () -> Category.OTHERS);
    }

    public BigDecimal getPrice() {
        return Objects.requireNonNullElseGet(this.price, () -> BigDecimal.ZERO);
    }
}

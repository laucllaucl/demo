package com.example.demo.services;

import com.example.demo.Category;
import com.example.demo.Item;
import com.example.demo.Location;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class PrinterServiceImpl implements PrinterService {

    @Autowired
    private CalculatorService calculatorService;

    private final String HR = "======================================";

    @Override
    public void printReceipt(String input) {

        try {
            Location location = getLocation(input);
            List<Item> items = getItemList(input);

            System.out.println(HR);
            System.out.format("%-15s %10s %10s %n", "item", "price", "qty");
            for (Item item : items) {
                System.out.format("%-15s %5s %5s %9s %n",
                        item.getName(),
                        "$", item.getPrice(),
                        item.getQuantity());
            }

            BigDecimal subTotal = calculatorService.getSubTotal(items);
            BigDecimal tax = calculatorService.getTax(items, location);

            System.out.format("%-15s %15s %5.2f %n", "subtotal:", "$", subTotal);
            System.out.format("%-15s %15s %5.2f %n", "tax:", "$", tax);
            System.out.format("%-15s %15s %5.2f %n", "total:", "$", calculatorService.getTotal(subTotal, tax));
            System.out.println(HR);
        } catch (Exception e) {
            System.out.println("Incorrect Input Format");
            System.out.println(e.getMessage());
        }
    }

    private Location getLocation(@NotNull String input) {
        Location location = null;
        List<Item> items = new ArrayList<>();
        List<String> inputItemsString = Arrays.asList(input.split(","));
        location = Location.valueOf(inputItemsString.get(0).split(":")[1].trim());

        return location;
    }

    private List<Item> getItemList(@NotNull String input) {
        List<Item> items = new ArrayList<>();
        List<String> inputItems = Arrays.asList(input.split(","));
        for (String itemString : inputItems) {
            if (inputItems.get(0).equals(itemString)) {
                continue;
            }

            String[] productAndPrice = itemString.split(" at ");
            String quantityProduct = productAndPrice[0].trim();
            int quantity = Integer.parseInt(quantityProduct.split(" ")[0]);
            String productName = quantityProduct.substring(quantityProduct.indexOf(" ")).trim();
            BigDecimal price = new BigDecimal(productAndPrice[1].trim());

            Item item = new Item(productName, Category.getCategoryByProductName(productName.toLowerCase()), price, quantity);
            items.add(item);
        }

        return items;
    }
}

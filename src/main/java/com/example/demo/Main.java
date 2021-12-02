package com.example.demo;

import com.example.demo.services.PrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Main implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private PrinterService printerService;

    @Autowired
    private ApplicationContext appContext;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        while (true) {
            System.out.print("input location and items with quantity and price or type \"exit\" to quit: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if ("exit".equalsIgnoreCase(input)) {
                scanner.close();
                SpringApplication.exit(appContext, () -> 0);
                return;
            }

            printerService.printReceipt(input);
        }
    }
}

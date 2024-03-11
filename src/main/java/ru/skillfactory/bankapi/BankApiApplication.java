package ru.skillfactory.bankapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.skillfactory.bankapi.controller.Controller;

import java.io.IOException;
import java.util.logging.LogManager;

@SpringBootApplication
public class BankApiApplication {

    public static void main(String[] args) {
        //try {
        //LogManager.getLogManager().readConfiguration(BankApiApplication.class.getResourceAsStream("/logging.properties"));
        //} catch (IOException e) {
        //throw new RuntimeException(e);
        //}

        SpringApplication.run(BankApiApplication.class, args);
    }
}

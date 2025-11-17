// src/main/java/com/example/Pratica4/controller/HomeController.java
package com.example.Pratica4.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Aplicação funcionando!";
    }
}
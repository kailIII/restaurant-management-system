package com.hotelvictoria.restaurants.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("*")
    public String index() {
        return "index";
    }

    @GetMapping("/manager/**/*")
    public String manager() {
        return "index";
    }
}

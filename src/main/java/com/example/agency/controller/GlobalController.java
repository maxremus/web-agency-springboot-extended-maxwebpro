package com.example.agency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class GlobalController {

    @ModelAttribute
    public void global(Model model) {
        model.addAttribute("baseUrl", System.getProperty("site.baseUrl", "http://localhost:8080"));
    }
}

package com.project.PropertyVersatile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Handles GET requests for displaying the index page
    @GetMapping("/index")
    public String index() {
        // Returns the name of the template for rendering the index page
        return "index";
    }
}

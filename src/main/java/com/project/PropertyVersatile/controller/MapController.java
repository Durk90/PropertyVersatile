package com.project.PropertyVersatile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {

    @GetMapping("/engineers")
    public String showMaintenanceRequestsMap(Model model) {
       
        model.addAttribute("mapMessage", "Welcome to the Maintenance Requests Map!");

        // Return the Thymeleaf template name 
        return "engineers";
    }
}

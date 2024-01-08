package com.project.PropertyVersatile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MaintenanceController {

    @GetMapping("/maintenance")
    public String index() {
        return "maintenance";
    }
}

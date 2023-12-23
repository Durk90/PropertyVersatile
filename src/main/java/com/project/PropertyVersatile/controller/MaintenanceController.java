package com.project.PropertyVersatile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.PropertyVersatile.entity.Maintenance;
import com.project.PropertyVersatile.service.MaintenanceService;

import java.util.List;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @Autowired
    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }
    
    @GetMapping("/create")
    public String showCreateMaintenanceForm(Model model) {
        model.addAttribute("maintenance", new Maintenance());
        return "create-maintenance";
    }

    @PostMapping("/create")
    public String createMaintenance(@ModelAttribute Maintenance maintenance) {
        maintenanceService.createMaintenance(maintenance);
        return "redirect:/maintenance";
    }

    @GetMapping
    public String getAllMaintenanceRequests(Model model) {
        try {
            List<Maintenance> maintenanceRequests = maintenanceService.getAllMaintenance();
            model.addAttribute("maintenanceRequests", maintenanceRequests);
            return "maintenance-requests";
        } catch (Exception e) {
            // Log the exception or handle it as needed
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving maintenance requests");
            return "error"; // Create an error.html template to display error messages
        }
    }

    @GetMapping("/{maintenanceId}")
    public String getMaintenanceRequestById(@PathVariable int maintenanceId, Model model) {
        try {
            Maintenance maintenanceRequest = maintenanceService.getMaintenanceById(maintenanceId);
            model.addAttribute("maintenanceRequest", maintenanceRequest);
            return "maintenance-request-details";
        } catch (Exception e) {
            // Log the exception or handle it as needed
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving maintenance request details");
            return "error"; // Create an error.html template to display error messages
        }
        
    }

    // Additional methods for creating, updating, and deleting maintenance requests if needed
}

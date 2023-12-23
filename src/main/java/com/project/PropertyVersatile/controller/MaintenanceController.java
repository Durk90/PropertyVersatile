package com.project.PropertyVersatile.controller;

import com.project.PropertyVersatile.entity.Maintenance;
import com.project.PropertyVersatile.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "create-maintenance"; // create-maintenance.html
    }

    @PostMapping("/create")
    public String createMaintenance(@ModelAttribute Maintenance maintenance, Model model) {
        try {
            maintenanceService.createMaintenance(maintenance);
            return "redirect:/maintenance";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error creating maintenance");
            return "error"; // error.html
        }
    }

    @GetMapping("/")
    public String getAllMaintenanceRequests(Model model) {
        try {
            List<Maintenance> maintenanceRequests = maintenanceService.getAllMaintenance();
            model.addAttribute("maintenanceRequests", maintenanceRequests);
            return "maintenance-requests"; // maintenance-requests.html
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving maintenance requests");
            return "error"; // error.html
        }
    }

    @GetMapping("/{maintenanceId}")
    public String getMaintenanceRequestById(@PathVariable int maintenanceId, Model model) {
        try {
            Maintenance maintenanceRequest = maintenanceService.getMaintenanceById(maintenanceId);
            model.addAttribute("maintenanceRequest", maintenanceRequest);
            return "maintenance-request-details"; // maintenance-request-details.html
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving maintenance request details");
            return "error"; // error.html
        }
    }

    // Additional methods for creating, updating, and deleting maintenance requests if needed
}

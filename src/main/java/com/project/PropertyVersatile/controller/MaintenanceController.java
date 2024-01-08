package com.project.PropertyVersatile.controller;

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

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @GetMapping
    public String getAllMaintenance(Model model) {
        try {
            List<Maintenance> maintenanceRequests = maintenanceService.getAllMaintenance();
            model.addAttribute("maintenanceRequests", maintenanceRequests);
            return "maintenance";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving maintenance requests");
            return "error";
        }
    }

    @GetMapping("/create")
    public String showCreateMaintenanceForm(Model model) {
        model.addAttribute("maintenance", new Maintenance());
        return "maintenance";
    }

    @PostMapping("/create")
    public String createMaintenance(@ModelAttribute Maintenance maintenance, Model model) {
        try {
            Maintenance createdMaintenance = maintenanceService.createMaintenance(maintenance);
            if (createdMaintenance != null) {
                return "redirect:/maintenance";
            } else {
                model.addAttribute("error", "Error creating maintenance request. Please check your input.");
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error creating maintenance request");
            return "error";
        }
    }

    @GetMapping("/{maintenanceId}/edit")
    public String showEditMaintenanceForm(@PathVariable int maintenanceId, Model model) {
        try {
            Maintenance maintenance = maintenanceService.getMaintenanceById(maintenanceId);
            model.addAttribute("maintenance", maintenance);
            return "edit-maintenance";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving maintenance details for editing");
            return "error";
        }
    }

    @PostMapping("/{maintenanceId}/edit")
    public String updateMaintenance(@PathVariable int maintenanceId, @ModelAttribute Maintenance updatedMaintenance, Model model) {
        try {
            Maintenance updated = maintenanceService.updateMaintenance(maintenanceId, updatedMaintenance);
            if (updated != null) {
                return "redirect:/maintenance";
            } else {
                model.addAttribute("error", "Error updating maintenance request. Please check your input.");
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error updating maintenance request");
            return "error";
        }
    }

    @GetMapping("/{maintenanceId}/delete")
    public String deleteMaintenance(@PathVariable int maintenanceId, Model model) {
        try {
            boolean deleted = maintenanceService.deleteMaintenance(maintenanceId);
            if (deleted) {
                return "redirect:/maintenance";
            } else {
                model.addAttribute("error", "Error deleting maintenance request");
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error deleting maintenance request");
            return "error";
        }
    }
}

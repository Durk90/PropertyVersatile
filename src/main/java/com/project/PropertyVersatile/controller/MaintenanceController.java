package com.project.PropertyVersatile.controller;

import com.project.PropertyVersatile.entity.Maintenance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    
    @GetMapping("/{maintenanceId}")
    public String getMaintenanceById(@PathVariable int maintenanceId, Model model) {
        try {
            Maintenance maintenance = maintenanceService.getMaintenanceById(maintenanceId);
            model.addAttribute("maintenance", maintenance);
            return "maintenance-details";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving maintenance details");
            return "error";
        }
    }


    @GetMapping("/create")
    public String showCreateMaintenanceForm(Model model) {        
            // Fetch the list of property IDs
            List<Integer> propertyIds = maintenanceService.getAllPropertyIds();

            // Add the property IDs to the model
            model.addAttribute("propertyIds", propertyIds);

            // Add an empty Maintenance object to the model
            model.addAttribute("maintenance", new Maintenance());

            return "create-maintenance";
        }


    @PostMapping("/create")
    public String createMaintenance(@ModelAttribute Maintenance maintenance, Model model) {
        try {
            maintenanceService.createMaintenance(maintenance);
            // Redirect to the "/maintenance" URL after successfully creating the maintenance request
            return "redirect:/maintenance";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error creating maintenance request. Please check your input.");
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

    @GetMapping("/{maintenanceId}/delete")
    public String deleteMaintenance(@PathVariable int maintenanceId, Model model) {
        try {
            maintenanceService.deleteMaintenance(maintenanceId);
            // Redirect to the "/maintenance" URL after successfully deleting the maintenance request
            return "redirect:/maintenance";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error deleting maintenance request");
            return "error";
        }
    }

    
    @PostMapping("/update")
    public String updateMaintenance(@ModelAttribute Maintenance updatedMaintenance, Model model) {
        try {
            // Call the updateMaintenance method in the maintenanceService to update the maintenance request
            maintenanceService.updateMaintenance(updatedMaintenance.getMaintenanceId(), updatedMaintenance);

            // Redirect to the "/maintenance" URL after successfully updating the maintenance request
            return "redirect:/maintenance";
        } catch (Exception e) {
            // If an exception occurs during the update operation:
            e.printStackTrace();

            // Add an error attribute to the model with an error message
            model.addAttribute("error", "Error updating maintenance request");

            // Return the name of the error view (e.g., "error")
            return "error";
        }
    }


}

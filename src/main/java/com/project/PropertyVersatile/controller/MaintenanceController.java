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

    // Service to handle maintenance-related operations
    private final MaintenanceService maintenanceService;

    @Autowired
    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    // Handles GET requests for retrieving all maintenance requests
    @GetMapping
    public String getAllMaintenance(Model model) {
        try {
            // Retrieve all maintenance requests from the service
            List<Maintenance> maintenanceRequests = maintenanceService.getAllMaintenance();

            // Add maintenance requests to the model for rendering in the view
            model.addAttribute("maintenanceRequests", maintenanceRequests);

            // Return the name of the view template (e.g., "maintenance")
            return "maintenance";
        } catch (Exception e) {
            // Handle exceptions by printing the stack trace and redirecting to an error page
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving maintenance requests");
            return "error";
        }
    }

    // Handles GET requests for retrieving details of a specific maintenance request by ID
    @GetMapping("/{maintenanceId}")
    public String getMaintenanceById(@PathVariable int maintenanceId, Model model) {
        try {
            // Retrieve maintenance details for the specified ID from the service
            Maintenance maintenance = maintenanceService.getMaintenanceById(maintenanceId);

            // Add maintenance details to the model for rendering in the view
            model.addAttribute("maintenance", maintenance);

            // Return the name of the view template for displaying maintenance details
            return "maintenance-details";
        } catch (Exception e) {
            // Handle exceptions by printing the stack trace and redirecting to an error page
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving maintenance details");
            return "error";
        }
    }

    // Handles GET requests for displaying the form to create a new maintenance request
    @GetMapping("/create")
    public String showCreateMaintenanceForm(Model model) {
        try {
            // Fetch the list of property IDs from the service
            List<Integer> propertyIds = maintenanceService.getAllPropertyIds();

            // Add property IDs and an empty Maintenance object to the model for rendering in the view
            model.addAttribute("propertyIds", propertyIds);
            model.addAttribute("maintenance", new Maintenance());

            // Return the name of the view template for creating a maintenance request
            return "create-maintenance";
        } catch (Exception e) {
            // Handle exceptions by printing the stack trace and redirecting to an error page
            e.printStackTrace();
            model.addAttribute("error", "Error preparing maintenance creation form");
            return "error";
        }
    }

    // Handles POST requests for creating a new maintenance request
    @PostMapping("/create")
    public String createMaintenance(@ModelAttribute Maintenance maintenance, Model model) {
        try {
            // Call the service to create a new maintenance request
            maintenanceService.createMaintenance(maintenance);

            // Redirect to the "/maintenance" URL after successfully creating the maintenance request
            return "redirect:/maintenance";
        } catch (Exception e) {
            // Handle exceptions by printing the stack trace and redirecting to an error page
            e.printStackTrace();
            model.addAttribute("error", "Error creating maintenance request. Please check your input.");
            return "error";
        }
    }

    // Handles GET requests for displaying the form to edit an existing maintenance request
    @GetMapping("/{maintenanceId}/edit")
    public String showEditMaintenanceForm(@PathVariable int maintenanceId, Model model) {
        try {
            // Retrieve maintenance details for editing from the service
            Maintenance maintenance = maintenanceService.getMaintenanceById(maintenanceId);

            // Add maintenance details to the model for rendering in the edit form view
            model.addAttribute("maintenance", maintenance);

            // Return the name of the view template for editing a maintenance request
            return "edit-maintenance";
        } catch (Exception e) {
            // Handle exceptions by printing the stack trace and redirecting to an error page
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving maintenance details for editing");
            return "error";
        }
    }

    // Handles GET requests for deleting an existing maintenance request
    @GetMapping("/{maintenanceId}/delete")
    public String deleteMaintenance(@PathVariable int maintenanceId, Model model) {
        try {
            // Call the service to delete the specified maintenance request
            maintenanceService.deleteMaintenance(maintenanceId);

            // Redirect to the "/maintenance" URL after successfully deleting the maintenance request
            return "redirect:/maintenance";
        } catch (Exception e) {
            // Handle exceptions by printing the stack trace and redirecting to an error page
            e.printStackTrace();
            model.addAttribute("error", "Error deleting maintenance request");
            return "error";
        }
    }

    // Handles POST requests for updating an existing maintenance request
    @PostMapping("/update")
    public String updateMaintenance(@ModelAttribute Maintenance updatedMaintenance, Model model) {
        try {
            // Call the service to update the specified maintenance request
            maintenanceService.updateMaintenance(updatedMaintenance.getMaintenanceId(), updatedMaintenance);

            // Redirect to the "/maintenance" URL after successfully updating the maintenance request
            return "redirect:/maintenance";
        } catch (Exception e) {
            // Handle exceptions by printing the stack trace and redirecting to an error page
            e.printStackTrace();
            model.addAttribute("error", "Error updating maintenance request");
            return "error";
        }
    }
}

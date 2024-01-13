package com.project.PropertyVersatile.controller;

import com.project.PropertyVersatile.entity.Property;
import com.project.PropertyVersatile.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/properties")
public class PropertyController {

    // Service to handle property-related operations
    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    // Handles GET requests for retrieving all properties
    @GetMapping
    public String getAllProperties(Model model) {
        try {
            // Retrieve all properties from the service
            List<Property> properties = propertyService.getAllProperties();

            // Add properties to the model for rendering in the view
            model.addAttribute("properties", properties);

            // Return the name of the view template (e.g., "properties")
            return "properties";
        } catch (Exception e) {
            // Handle exceptions by printing the stack trace and redirecting to an error page
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving properties");
            return "error";
        }
    }

    // Handles GET requests for retrieving details of a specific property by ID
    @GetMapping("/{propertyId}")
    public String getPropertyById(@PathVariable int propertyId, Model model) {
        try {
            // Retrieve property details for the specified ID from the service
            Property property = propertyService.getPropertyById(propertyId);

            // Add property details to the model for rendering in the view
            model.addAttribute("property", property);

            // Return the name of the view template for displaying property details
            return "property-details";
        } catch (Exception e) {
            // Handle exceptions by printing the stack trace and redirecting to an error page
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving property details");
            return "error";
        }
    }

    // Handles GET requests for displaying the form to add a new property
    @GetMapping("/create")
    public String showAddPropertyForm(Model model) {
        // Add an empty Property object to the model for rendering in the add property form view
        model.addAttribute("property", new Property());

        // Return the name of the view template for adding a property
        return "add-property";
    }

    // Handles POST requests for adding a new property
    @PostMapping("/create")
    public String addProperty(@ModelAttribute Property property, Model model) {
        try {
            // Call the service to add a new property
            propertyService.createProperty(property);

            // Redirect to the "/properties" URL after successfully adding the property
            return "redirect:/properties";
        } catch (Exception e) {
            // Handle exceptions by printing the stack trace and redirecting to an error page
            e.printStackTrace();
            model.addAttribute("error", "Error adding property");
            return "error";
        }
    }

    // Handles GET requests for displaying the form to edit an existing property
    @GetMapping("/{propertyId}/edit")
    public String showEditPropertyForm(@PathVariable int propertyId, Model model) {
        try {
            // Retrieve property details for editing from the service
            Property property = propertyService.getPropertyById(propertyId);

            // Add property details to the model for rendering in the edit form view
            model.addAttribute("property", property);

            // Return the name of the view template for editing a property
            return "edit-property";
        } catch (Exception e) {
            // Handle exceptions by printing the stack trace and redirecting to an error page
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving property for editing");
            return "error";
        }
    } 

    // Handles GET requests for deleting an existing property
    @GetMapping("/{propertyId}/delete")
    public String deleteProperty(@PathVariable int propertyId, Model model) {
        try {
            // Check if there are maintenance requests associated with the property
            boolean hasMaintenanceRequests = propertyService.hasMaintenanceRequests(propertyId);

            // If there are maintenance requests, include a warning parameter in the redirect URL
            if (hasMaintenanceRequests) {
                return "redirect:/properties?warning=Cannot+delete+property+with+associated+maintenance+requests.";
            }

            // If no maintenance requests, proceed with property deletion
            propertyService.deleteProperty(propertyId);

            // Redirect to the "/properties" URL after successfully deleting the property
            return "redirect:/properties";
        } catch (Exception e) {
            // Handle exceptions by printing the stack trace and redirecting to an error page
            e.printStackTrace();
            model.addAttribute("error", "Error deleting property");
            return "error";
        }
    }

    // Handles POST requests for updating an existing property
    @PostMapping("/update")
    public String updateProperty(@ModelAttribute Property property, Model model) {
        try {
            // Call the service to update the specified property
            propertyService.updateProperty(property.getPropertyId(), property);

            // Redirect to the "/properties" URL after successfully updating the property
            return "redirect:/properties";
        } catch (Exception e) {
            // Handle exceptions by printing the stack trace and redirecting to an error page
            e.printStackTrace();
            model.addAttribute("error", "Error editing property");
            return "error";
        }
    }
}

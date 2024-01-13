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

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public String getAllProperties(Model model) {
        try {
            List<Property> properties = propertyService.getAllProperties();
            model.addAttribute("properties", properties);
            return "properties";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving properties");
            return "error";
        }
    }

    @GetMapping("/{propertyId}")
    public String getPropertyById(@PathVariable int propertyId, Model model) {
        try {
            Property property = propertyService.getPropertyById(propertyId);
            model.addAttribute("property", property);
            return "property-details";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving property details");
            return "error";
        }
    }

    @GetMapping("/create")
    public String showAddPropertyForm(Model model) {
        model.addAttribute("property", new Property());
        return "add-property";
    }

    @PostMapping("/create")
    public String addProperty(@ModelAttribute Property property, Model model) {
        try {
            propertyService.createProperty(property);
            return "redirect:/properties";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error adding property");
            return "error";
        }
    }

    @GetMapping("/{propertyId}/edit")
    public String showEditPropertyForm(@PathVariable int propertyId, Model model) {
        try {
            Property property = propertyService.getPropertyById(propertyId);
            model.addAttribute("property", property);
            return "edit-property";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving property for editing");
            return "error";
        }
    } 

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
            e.printStackTrace();
            model.addAttribute("error", "Error deleting property request");
            return "error";
        }
    }


    @PostMapping("/update")
    public String updateProperty(@ModelAttribute Property property, Model model) {
        try {
            propertyService.updateProperty(property.getPropertyId(), property);
            return "redirect:/properties";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error editing property");
            return "error";
        }
    }
}

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
            return "properties"; // properties.html
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving properties");
            return "error"; // error.html
        }
    }

    @GetMapping("/{propertyId}")
    public String getPropertyById(@PathVariable int propertyId, Model model) {
        try {
            Property property = propertyService.getPropertyById(propertyId);
            model.addAttribute("property", property);
            return "property-details"; // property-details.html
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving property details");
            return "error"; // error.html
        }
    }

    @GetMapping("/create")
    public String showAddPropertyForm(Model model) {
        model.addAttribute("property", new Property());
        return "add-property"; // add-property.html
    }

    @PostMapping("/create")
    public String addProperty(@ModelAttribute Property property, Model model) {
        try {
            propertyService.createProperty(property);
            return "redirect:/properties";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error adding property");
            return "error"; // error.html
        }
    }

    @GetMapping("/{propertyId}/edit")
    public String showEditPropertyForm(@PathVariable int propertyId, Model model) {
        try {
            Property property = propertyService.getPropertyById(propertyId);
            model.addAttribute("property", property);
            return "edit-property"; // edit-property.html
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving property for editing");
            return "error"; // error.html
        }
    }

    @PostMapping("/{propertyId}/edit")
    public String editProperty(@PathVariable int propertyId, @ModelAttribute Property property, Model model) {
        try {
            property.setPropertyId(propertyId);
            propertyService.updateProperty(propertyId, property);
            return "redirect:/properties";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error editing property");
            return "error"; // error.html
        }
    }

    @GetMapping("/{propertyId}/delete")
    public String deleteProperty(@PathVariable int propertyId, Model model) {
        try {
            propertyService.deleteProperty(propertyId);
            return "redirect:/properties";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error deleting property");
            return "error"; // error.html
        }
    }
}

package com.project.PropertyVersatile.controller;

import com.project.PropertyVersatile.controller.PropertyController;
import com.project.PropertyVersatile.entity.Property;
import com.project.PropertyVersatile.service.PropertyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PropertyControllerTest {

    @InjectMocks
    private PropertyController propertyController;

    @Mock
    private PropertyService propertyService;

    @Mock
    private Model model;

    @Test
    public void testGetAllProperties() {
        // Test to ensure retrieving all properties works correctly
        when(propertyService.getAllProperties()).thenReturn(Collections.emptyList());

        String result = propertyController.getAllProperties(model);

        assertEquals("properties", result);  // Expecting the view name to be "properties"
        verify(model).addAttribute(eq("properties"), anyList());  // Expecting the model to contain a "properties" attribute with an empty list
    }

    @Test
    public void testGetPropertyById() {
        // Test to ensure retrieving a property by ID works correctly
        int propertyId = 1;
        Property property = new Property();
        when(propertyService.getPropertyById(propertyId)).thenReturn(property);

        String result = propertyController.getPropertyById(propertyId, model);

        assertEquals("property-details", result);  // Expecting the view name to be "property-details"
        verify(model).addAttribute(eq("property"), eq(property));  // Expecting the model to contain a "property" attribute with the correct property
    }

    @Test
    public void testShowAddPropertyForm() {
        // Test to ensure showing the add property form works correctly
        String result = propertyController.showAddPropertyForm(model);

        assertEquals("add-property", result);  // Expecting the view name to be "add-property"
        verify(model).addAttribute(eq("property"), any(Property.class));  // Expecting the model to contain a "property" attribute with a new Property object
    }

    @Test
    public void testAddProperty() {
        // Test to ensure adding a property works correctly
        Property property = new Property();
        when(propertyService.createProperty(property)).thenReturn(property);

        String result = propertyController.addProperty(property, model);

        assertEquals("redirect:/properties", result);  // Expecting a redirect to "/properties"
        verify(propertyService).createProperty(property);  // Expecting the createProperty method to be called with the correct property
    }

    @Test
    public void testShowEditPropertyForm() {
        // Test to ensure showing the edit property form works correctly
        int propertyId = 1;
        Property property = new Property();
        when(propertyService.getPropertyById(propertyId)).thenReturn(property);

        String result = propertyController.showEditPropertyForm(propertyId, model);

        assertEquals("edit-property", result);  // Expecting the view name to be "edit-property"
        verify(model).addAttribute(eq("property"), eq(property));  // Expecting the model to contain a "property" attribute with the correct property
    }

    @Test
    public void testDeletePropertyWithNoMaintenanceRequests() {
        // Test to ensure deleting a property with no maintenance requests works correctly
        int propertyId = 1;
        when(propertyService.hasMaintenanceRequests(propertyId)).thenReturn(false);

        String result = propertyController.deleteProperty(propertyId, model);

        assertEquals("redirect:/properties", result);  // Expecting a redirect to "/properties"
        verify(propertyService).deleteProperty(propertyId);  // Expecting the deleteProperty method to be called with the correct property ID
    }

    @Test
    public void testDeletePropertyWithMaintenanceRequests() {
        // Test to ensure deleting a property with maintenance requests works correctly
        int propertyId = 1;
        when(propertyService.hasMaintenanceRequests(propertyId)).thenReturn(true);

        String result = propertyController.deleteProperty(propertyId, model);

        assertEquals("redirect:/properties?warning=Cannot+delete+property+with+associated+maintenance+requests.", result);
        // Expecting a redirect to "/properties" with a warning parameter
        verify(propertyService, never()).deleteProperty(propertyId);  // Expecting the deleteProperty method not to be called
    }

    @Test
    public void testUpdateProperty() {
        // Test to ensure updating a property works correctly
        Property property = new Property();
        property.setPropertyId(1);

        String result = propertyController.updateProperty(property, model);

        assertEquals("redirect:/properties", result);  // Expecting a redirect to "/properties"
        verify(propertyService).updateProperty(eq(1), eq(property));  // Expecting the updateProperty method to be called with the correct property ID and property
    }
}

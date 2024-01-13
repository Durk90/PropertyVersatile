package com.project.PropertyVersatile;

import com.project.PropertyVersatile.entity.Maintenance;
import com.project.PropertyVersatile.entity.Property;
import com.project.PropertyVersatile.repository.MaintenanceRepository;
import com.project.PropertyVersatile.repository.PropertyRepository;
import com.project.PropertyVersatile.service.PropertyService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTest {

    @InjectMocks
    private PropertyService propertyService;

    @Mock
    private PropertyRepository propertyRepository;

    @Mock
    private MaintenanceRepository maintenanceRepository;

    @Test
    public void testGetAllProperties() {
        // Test to ensure retrieving all properties works correctly
        when(propertyRepository.findAll()).thenReturn(Collections.emptyList());

        List<Property> result = propertyService.getAllProperties();

        assertNotNull(result);  // Expecting the result not to be null
        assertTrue(result.isEmpty());  // Expecting the result list to be empty
        verify(propertyRepository).findAll();  // Expecting the findAll method to be called on the propertyRepository
    }

    @Test
    public void testGetPropertyById() {
        // Test to ensure retrieving a property by ID works correctly
        int propertyId = 1;
        Property property = new Property();
        when(propertyRepository.findById(propertyId)).thenReturn(Optional.of(property));

        Property result = propertyService.getPropertyById(propertyId);

        assertNotNull(result);  // Expecting the result not to be null
        assertEquals(property, result);  // Expecting the result to be equal to the mock property
        verify(propertyRepository).findById(propertyId);  // Expecting the findById method to be called on the propertyRepository
    }

    @Test
    public void testCreateProperty() {
        // Test to ensure creating a property works correctly
        Property property = new Property();
        when(propertyRepository.save(any(Property.class))).thenReturn(property);

        Property result = propertyService.createProperty(property);

        assertNotNull(result);  // Expecting the result not to be null
        assertEquals(property, result);  // Expecting the result to be equal to the mock property
        verify(propertyRepository).save(property);  // Expecting the save method to be called on the propertyRepository
    }

    @Test
    public void testUpdateProperty() {
        // Test to ensure updating a property works correctly
        int propertyId = 1;
        Property existingProperty = new Property();
        existingProperty.setPropertyId(propertyId);
        Property updatedProperty = new Property();

        when(propertyRepository.findById(propertyId)).thenReturn(Optional.of(existingProperty));
        when(propertyRepository.save(any(Property.class))).thenReturn(updatedProperty);

        Property result = propertyService.updateProperty(propertyId, updatedProperty);

        assertNotNull(result);  // Expecting the result not to be null
        assertEquals(updatedProperty, result);  // Expecting the result to be equal to the mock updatedProperty
        verify(propertyRepository).findById(propertyId);  // Expecting the findById method to be called on the propertyRepository
        verify(propertyRepository).save(existingProperty);  // Expecting the save method to be called on the propertyRepository
    }

    @Test
    public void testDeletePropertyWithNoMaintenanceRequests() {
        // Test to ensure deleting a property with no maintenance requests works correctly
        int propertyId = 1;
        when(maintenanceRepository.findByPropertyId(propertyId)).thenReturn(Collections.emptyList());

        boolean result = propertyService.deleteProperty(propertyId);

        assertTrue(result);  // Expecting the result to be true
        verify(maintenanceRepository).findByPropertyId(propertyId);  // Expecting the findByPropertyId method to be called on the maintenanceRepository
        verify(propertyRepository).deleteById(propertyId);  // Expecting the deleteById method to be called on the propertyRepository
    }

    @Test
    public void testDeletePropertyWithMaintenanceRequests() {
        // Test to ensure deleting a property with maintenance requests works correctly
        int propertyId = 1;
        when(maintenanceRepository.findByPropertyId(propertyId)).thenReturn(Collections.singletonList(new Maintenance()));

        boolean result = propertyService.deleteProperty(propertyId);

        assertFalse(result);  // Expecting the result to be false
        verify(maintenanceRepository).findByPropertyId(propertyId);  // Expecting the findByPropertyId method to be called on the maintenanceRepository
        verify(propertyRepository, never()).deleteById(propertyId);  // Expecting the deleteById method not to be called on the propertyRepository
    }

    @Test
    public void testHasMaintenanceRequests() {
        // Test to ensure checking for maintenance requests works correctly when there are maintenance requests
        int propertyId = 1;
        when(maintenanceRepository.findByPropertyId(propertyId)).thenReturn(Collections.singletonList(new Maintenance()));

        boolean result = propertyService.hasMaintenanceRequests(propertyId);

        assertTrue(result);  // Expecting the result to be true
        verify(maintenanceRepository).findByPropertyId(propertyId);  // Expecting the findByPropertyId method to be called on the maintenanceRepository
    }

    @Test
    public void testHasNoMaintenanceRequests() {
        // Test to ensure checking for maintenance requests works correctly when there are no maintenance requests
        int propertyId = 1;
        when(maintenanceRepository.findByPropertyId(propertyId)).thenReturn(Collections.emptyList());

        boolean result = propertyService.hasMaintenanceRequests(propertyId);

        assertFalse(result);  // Expecting the result to be false
        verify(maintenanceRepository).findByPropertyId(propertyId);  // Expecting the findByPropertyId method to be called on the maintenanceRepository
    }
}

package com.project.PropertyVersatile.service;

import com.project.PropertyVersatile.entity.Maintenance;
import com.project.PropertyVersatile.entity.Property;
import com.project.PropertyVersatile.repository.MaintenanceRepository;
import com.project.PropertyVersatile.repository.PropertyRepository;
import com.project.PropertyVersatile.service.MaintenanceService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MaintenanceServiceTest {

    @InjectMocks
    private MaintenanceService maintenanceService;

    @Mock
    private MaintenanceRepository maintenanceRepository;

    @Mock
    private PropertyRepository propertyRepository;

    @Test
    public void testCreateMaintenance() {
        // Test to ensure maintenance creation works correctly
        Maintenance maintenance = new Maintenance();
        maintenance.setPropertyId(1);

        Property mockProperty = new Property();
        mockProperty.setPropertyId(1);  // Set the property ID

        when(propertyRepository.findById(eq(1))).thenReturn(Optional.of(mockProperty));
        when(maintenanceRepository.save(any())).thenAnswer(invocation -> {
            Maintenance savedMaintenance = invocation.getArgument(0);
            assertNotNull(savedMaintenance);  // Ensure the saved maintenance is not null
            assertEquals(mockProperty, savedMaintenance.getProperty());  // Verify the saved maintenance has the correct property
            return savedMaintenance;
        });

        Maintenance result = maintenanceService.createMaintenance(maintenance);

        assertNotNull(result);  // Expecting a non-null result
        assertEquals(maintenance, result);  // Expecting the same maintenance object
    }



    @Test
    public void testCreateMaintenancePropertyNotFound() {
        // Test to ensure handling when the associated property is not found
        Maintenance maintenance = new Maintenance();
        maintenance.setPropertyId(1);

        when(propertyRepository.findById(eq(1))).thenReturn(Optional.empty());

        Maintenance result = maintenanceService.createMaintenance(maintenance);

        assertNull(result);  // Expecting null result when property is not found
    }

    @Test
    public void testGetAllMaintenance() {
        // Test to ensure retrieving all maintenance requests works correctly
        when(maintenanceRepository.findAll()).thenReturn(Collections.emptyList());

        assertEquals(Collections.emptyList(), maintenanceService.getAllMaintenance());  // Expecting an empty list
    }

    @Test
    public void testGetMaintenanceById() {
        // Test to ensure retrieving a maintenance request by ID works correctly
        int maintenanceId = 1;
        Maintenance maintenance = new Maintenance();
        when(maintenanceRepository.findById(maintenanceId)).thenReturn(Optional.of(maintenance));

        assertEquals(maintenance, maintenanceService.getMaintenanceById(maintenanceId));  // Expecting the correct maintenance object
    }

    @Test
    public void testUpdateMaintenance() {
        // Test to ensure updating a maintenance request works correctly
        int maintenanceId = 1;
        Maintenance existingMaintenance = new Maintenance();
        existingMaintenance.setMaintenanceId(maintenanceId);

        Maintenance updatedMaintenance = new Maintenance();

        when(maintenanceRepository.findById(maintenanceId)).thenReturn(Optional.of(existingMaintenance));
        when(maintenanceRepository.save(existingMaintenance)).thenReturn(updatedMaintenance);

        assertEquals(updatedMaintenance, maintenanceService.updateMaintenance(maintenanceId, updatedMaintenance));  // Expecting the updated maintenance object
    }

    @Test
    public void testDeleteMaintenance() {
        // Test to ensure deleting a maintenance request works correctly
        int maintenanceId = 1;

        assertTrue(maintenanceService.deleteMaintenance(maintenanceId));  // Expecting true for successful deletion
        verify(maintenanceRepository).deleteById(maintenanceId);  // Expecting the deleteById method to be called
    }

    @Test
    public void testGetAllPropertyIds() {
        // Mock propertyRepository to return a list of Property objects
        List<Property> properties = Collections.singletonList(new Property());
        when(propertyRepository.findAll()).thenReturn(properties);

        // Assuming getAllPropertyIds returns a list of Property IDs
        List<Integer> result = maintenanceService.getAllPropertyIds();

        // Extract property IDs from the list of Property objects
        List<Integer> expectedPropertyIds = properties.stream().map(Property::getPropertyId).collect(Collectors.toList());

        assertEquals(expectedPropertyIds, result);
    }


}

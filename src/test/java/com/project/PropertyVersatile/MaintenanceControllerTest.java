package com.project.PropertyVersatile;

import com.project.PropertyVersatile.controller.MaintenanceController;
import com.project.PropertyVersatile.entity.Maintenance;
import com.project.PropertyVersatile.service.MaintenanceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MaintenanceControllerTest {

    @InjectMocks
    private MaintenanceController maintenanceController;

    @Mock
    private MaintenanceService maintenanceService;

    @Mock
    private Model model;

    @Test
    public void testGetAllMaintenance() {
        // Test to ensure the controller returns the correct view name
        // and adds the list of maintenance requests to the model.
        when(maintenanceService.getAllMaintenance()).thenReturn(Collections.emptyList());

        String result = maintenanceController.getAllMaintenance(model);

        assertEquals("maintenance", result);  // Expecting the view name "maintenance"
        verify(model).addAttribute(eq("maintenanceRequests"), anyList());  // Expecting the list attribute in the model
    }

    @Test
    public void testGetMaintenanceById() {
        // Test to ensure the controller returns the correct view name
        // and adds the maintenance details to the model.
        int maintenanceId = 1;
        Maintenance maintenance = new Maintenance();
        when(maintenanceService.getMaintenanceById(maintenanceId)).thenReturn(maintenance);

        String result = maintenanceController.getMaintenanceById(maintenanceId, model);

        assertEquals("maintenance-details", result);  // Expecting the view name "maintenance-details"
        verify(model).addAttribute(eq("maintenance"), eq(maintenance));  // Expecting the maintenance attribute in the model
    }

}

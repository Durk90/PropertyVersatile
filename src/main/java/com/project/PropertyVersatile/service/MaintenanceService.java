package com.project.PropertyVersatile.service;

import com.project.PropertyVersatile.entity.Maintenance;
import com.project.PropertyVersatile.entity.Property;
import com.project.PropertyVersatile.repository.MaintenanceRepository;
import com.project.PropertyVersatile.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Transactional
public class MaintenanceService {

    // Repositories for interacting with Maintenance and Property entities
    private final MaintenanceRepository maintenanceRepository;
    private final PropertyRepository propertyRepository;

    // Logger for logging messages
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public MaintenanceService(MaintenanceRepository maintenanceRepository, PropertyRepository propertyRepository) {
        this.maintenanceRepository = maintenanceRepository;
        this.propertyRepository = propertyRepository;
    }

    // Retrieves all maintenance records from the database
    public List<Maintenance> getAllMaintenance() {
        return maintenanceRepository.findAll();
    }

    // Retrieves a specific maintenance record by its ID
    public Maintenance getMaintenanceById(int maintenanceId) {
        Optional<Maintenance> maintenanceOptional = maintenanceRepository.findById(maintenanceId);
        return maintenanceOptional.orElse(null);
    }

    // Creates a new maintenance record in the database
    public Maintenance createMaintenance(Maintenance maintenance) {
        try {
            // Fetch the associated Property entity using propertyId
            int propertyId = maintenance.getPropertyId();
            Optional<Property> propertyOptional = propertyRepository.findById(propertyId);
            
            // If Property entity is found, associate it with the Maintenance entity and save
            if (propertyOptional.isPresent()) {
                Property property = propertyOptional.get();
                maintenance.setProperty(property);
                return maintenanceRepository.save(maintenance);
            } else {
                // Handle the case where Property with propertyId is not found
                logger.log(Level.SEVERE, "Property with id " + propertyId + " not found");
                return null;
            }
        } catch (Exception e) {
            // Log the exception
            logger.log(Level.SEVERE, "Error creating maintenance", e);
            return null;
        }
    }

    // Updates an existing maintenance record in the database
    public Maintenance updateMaintenance(int maintenanceId, Maintenance updatedMaintenance) {
        try {
            // Retrieve the existing maintenance record by ID
            Optional<Maintenance> existingMaintenanceOptional = maintenanceRepository.findById(maintenanceId);
            return existingMaintenanceOptional.map(existingMaintenance -> {
                // Update the fields of the existing maintenance record
                existingMaintenance.setMaintenanceDate(updatedMaintenance.getMaintenanceDate());
                existingMaintenance.setDescription(updatedMaintenance.getDescription());
                existingMaintenance.setCost(updatedMaintenance.getCost());
                // Save the updated maintenance record
                return maintenanceRepository.save(existingMaintenance);
            }).orElse(null); // Handle the case where maintenanceId is not found
        } catch (Exception e) {
            // Log the exception
            logger.log(Level.SEVERE, "Error updating maintenance with ID: " + maintenanceId, e);
            return null;
        }
    }

    // Deletes a maintenance record from the database
    public boolean deleteMaintenance(int maintenanceId) {
        try {
            maintenanceRepository.deleteById(maintenanceId);
            return true;
        } catch (Exception e) {
            // Log the exception
            logger.log(Level.SEVERE, "Error deleting maintenance with ID: " + maintenanceId, e);
            return false;
        }
    }

    // Retrieves a list of all property IDs
    public List<Integer> getAllPropertyIds() {
        try {
            // Fetch all properties from the repository and extract their IDs
            return propertyRepository.findAll().stream()
                    .map(Property::getPropertyId)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // Log the exception
            logger.log(Level.SEVERE, "Error retrieving property IDs", e);
            return Collections.emptyList();
        }
    }
}

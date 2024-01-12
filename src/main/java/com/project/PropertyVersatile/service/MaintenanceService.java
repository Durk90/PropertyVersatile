package com.project.PropertyVersatile.service;

import com.project.PropertyVersatile.entity.Maintenance;
import com.project.PropertyVersatile.entity.Property; // Import Property entity
import com.project.PropertyVersatile.repository.MaintenanceRepository;
import com.project.PropertyVersatile.repository.PropertyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final PropertyRepository propertyRepository;
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public MaintenanceService(MaintenanceRepository maintenanceRepository, PropertyRepository propertyRepository) {
        this.maintenanceRepository = maintenanceRepository;
        this.propertyRepository = propertyRepository;
    }

    public List<Maintenance> getAllMaintenance() {
        return maintenanceRepository.findAll();
    }

    public Maintenance getMaintenanceById(int maintenanceId) {
        Optional<Maintenance> maintenanceOptional = maintenanceRepository.findById(maintenanceId);
        return maintenanceOptional.orElse(null);
    }

    public Maintenance createMaintenance(Maintenance maintenance) {
        try {
            // Fetch the Property entity from the database using propertyId
            int propertyId = maintenance.getPropertyId();
            Optional<Property> propertyOptional = propertyRepository.findById(propertyId);
            if (propertyOptional.isPresent()) {
                Property property = propertyOptional.get();

                // Set the fetched Property entity in the Maintenance entity
                maintenance.setProperty(property);

                // Save the Maintenance entity
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

    public Maintenance updateMaintenance(int maintenanceId, Maintenance updatedMaintenance) {
        try {
            Optional<Maintenance> existingMaintenanceOptional = maintenanceRepository.findById(maintenanceId);
            return existingMaintenanceOptional.map(existingMaintenance -> {              
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
}

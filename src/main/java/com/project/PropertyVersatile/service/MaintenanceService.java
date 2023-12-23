package com.project.PropertyVersatile.service;

import com.project.PropertyVersatile.entity.Maintenance;
import com.project.PropertyVersatile.repository.MaintenanceRepository;

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
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public MaintenanceService(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
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
            // Additional validation logic if needed
            return maintenanceRepository.save(maintenance);
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
                // Update the fields based on your requirements
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

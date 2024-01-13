package com.project.PropertyVersatile.service;

import com.project.PropertyVersatile.entity.Property;
import com.project.PropertyVersatile.repository.PropertyRepository;
import com.project.PropertyVersatile.entity.Maintenance;
import com.project.PropertyVersatile.repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final MaintenanceRepository maintenanceRepository;
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public PropertyService(PropertyRepository propertyRepository, MaintenanceRepository maintenanceRepository) {
        this.propertyRepository = propertyRepository;
        this.maintenanceRepository = maintenanceRepository;
    }

    public List<Property> getAllProperties() {
        try {
            List<Property> properties = propertyRepository.findAll();
            logger.info("Number of properties retrieved: " + properties.size());
            return properties;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error retrieving all properties", e);
            return Collections.emptyList();
        }
    }

    public Property getPropertyById(int propertyId) {
        try {
            Optional<Property> propertyOptional = propertyRepository.findById(propertyId);
            return propertyOptional.orElse(null);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error retrieving property by ID: " + propertyId, e);
            return null;
        }
    }

    public Property createProperty(Property property) {
        try {
            return propertyRepository.save(property);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error creating property", e);
            return null;
        }
    }

    public Property updateProperty(int propertyId, Property updatedProperty) {
        try {
            Optional<Property> existingPropertyOptional = propertyRepository.findById(propertyId);
            return existingPropertyOptional.map(existingProperty -> {
                existingProperty.setPropertyName(updatedProperty.getPropertyName());
                existingProperty.setAddress(updatedProperty.getAddress());
                existingProperty.setConstructionDate(updatedProperty.getConstructionDate());
                existingProperty.setSquareFootage(updatedProperty.getSquareFootage());
                existingProperty.setPropertyType(updatedProperty.getPropertyType());
                // Update other fields as needed
                return propertyRepository.save(existingProperty);
            }).orElse(null);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating property with ID: " + propertyId, e);
            return null;
        }
    }

    public boolean deleteProperty(int propertyId) {
        try {
            // Check if there are maintenance requests associated with the property
            if (hasMaintenanceRequests(propertyId)) {
                logger.warning("Cannot delete property with associated maintenance requests.");
                return false;
            }

            propertyRepository.deleteById(propertyId);
            logger.info("Property deleted successfully. ID: " + propertyId);
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting property with ID: " + propertyId, e);
            return false;
        }
    }

    public boolean hasMaintenanceRequests(int propertyId) {
        try {
            List<Maintenance> maintenanceList = maintenanceRepository.findByPropertyId(propertyId);
            return !maintenanceList.isEmpty();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error checking for maintenance requests", e);
            return true; // Return true in case of an error (handle as needed)
        }
    }
}

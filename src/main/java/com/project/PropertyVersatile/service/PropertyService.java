package com.project.PropertyVersatile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.PropertyVersatile.entity.Property;
import com.project.PropertyVersatile.repository.PropertyRepository;
import java.util.Collections;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<Property> getAllProperties() {
        try {
            List<Property> properties = propertyRepository.findAll();

            // Log the properties
            logger.info("Number of properties retrieved: " + properties.size());

            return properties;
        } catch (Exception e) {
            // Log the exception
            logger.log(Level.SEVERE, "Error retrieving all properties", e);
            return Collections.emptyList();
        }
    }
    public Property getPropertyById(int propertyId) {
        try {
            Optional<Property> propertyOptional = propertyRepository.findById(propertyId);
            return propertyOptional.orElse(null);
        } catch (Exception e) {
            // Log the exception
            logger.log(Level.SEVERE, "Error retrieving property by ID: " + propertyId, e);
            return null;
        }
    }

    public Property createProperty(Property property) {
        try {
            // Additional validation logic if needed
            return propertyRepository.save(property);
        } catch (Exception e) {
            // Log the exception
            logger.log(Level.SEVERE, "Error creating property", e);
            return null;
        }
    }

    public Property updateProperty(int propertyId, Property updatedProperty) {
        try {
            Optional<Property> existingPropertyOptional = propertyRepository.findById(propertyId);
            return existingPropertyOptional.map(existingProperty -> {
                // Update the existing property with the new values
                existingProperty.setPropertyName(updatedProperty.getPropertyName());
                existingProperty.setAddress(updatedProperty.getAddress());
                // Update other fields as needed
                return propertyRepository.save(existingProperty);
            }).orElse(null); // Property not found
        } catch (Exception e) {
            // Log the exception
            logger.log(Level.SEVERE, "Error updating property with ID: " + propertyId, e);
            return null;
        }
    }

    public boolean deleteProperty(int propertyId) {
        try {
            propertyRepository.deleteById(propertyId);
            return true;
        } catch (Exception e) {
            // Log the exception
            logger.log(Level.SEVERE, "Error deleting property with ID: " + propertyId, e);
            return false;
        }
    }
}

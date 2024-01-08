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
            propertyRepository.deleteById(propertyId);
            logger.info("Property deleted successfully. ID: " + propertyId);
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting property with ID: " + propertyId, e);
            return false;
        }
    }
}

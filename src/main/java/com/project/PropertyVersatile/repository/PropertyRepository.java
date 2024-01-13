package com.project.PropertyVersatile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.PropertyVersatile.entity.Property;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

    // Custom query to find a property by its name
    Property findByPropertyName(String propertyName);

    // Custom query to find properties by their type
    List<Property> findByPropertyType(String propertyType);

    // Custom query to find properties constructed after a certain date
    List<Property> findByConstructionDateAfter(LocalDate date);

    // Custom query to find properties with square footage greater than a specified value
    List<Property> findBySquareFootageGreaterThan(int squareFootage);

    // Add more custom queries as needed

    // Handle error cases
    default Property findByPropertyNameSafe(String propertyName) {
        try {
            return findByPropertyName(propertyName);
        } catch (Exception e) {
            // Log the exception or handle it as needed
            e.printStackTrace();
            throw new RuntimeException("Error querying property by name", e);
        }
    }

}

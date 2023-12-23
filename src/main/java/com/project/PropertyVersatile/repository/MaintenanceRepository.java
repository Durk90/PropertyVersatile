package com.project.PropertyVersatile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.PropertyVersatile.entity.Maintenance;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {

    // Custom query to find maintenance by property ID
    List<Maintenance> findByPropertyId(int propertyId);

    // Custom query to find maintenance by maintenance date after a certain date
    List<Maintenance> findByMaintenanceDateAfter(LocalDate maintenanceDate);

    // Custom query to find maintenance with cost greater than a specified value
    List<Maintenance> findByCostGreaterThan(double cost);

    // Add more custom queries as needed

    // Handle error cases
    default List<Maintenance> findByMaintenanceDateAfterSafe(LocalDate maintenanceDate) {
        try {
            return findByMaintenanceDateAfter(maintenanceDate);
        } catch (Exception e) {
            // Log the exception or handle it as needed
            e.printStackTrace();
            throw new RuntimeException("Error querying maintenance by maintenance date", e);
        }
    }

    // Add similar methods for other custom queries
}

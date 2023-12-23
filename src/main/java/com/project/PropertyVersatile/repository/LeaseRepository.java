package com.project.PropertyVersatile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.PropertyVersatile.entity.Lease;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaseRepository extends JpaRepository<Lease, Integer> {

    // Custom query to find leases by property ID
    List<Lease> findByPropertyId(int propertyId);

    // Custom query to find leases by tenant name
    List<Lease> findByTenantName(String tenantName);

    // Custom query to find leases by start date after a certain date
    List<Lease> findByStartDateAfter(LocalDate startDate);

    // Custom query to find leases with monthly rent greater than a specified value
    List<Lease> findByMonthlyRentGreaterThan(double monthlyRent);

    // Add more custom queries as needed

    // Handle error cases
    default List<Lease> findByStartDateAfterSafe(LocalDate startDate) {
        try {
            return findByStartDateAfter(startDate);
        } catch (Exception e) {
            // Log the exception or handle it as needed
            e.printStackTrace();
            throw new RuntimeException("Error querying leases by start date", e);
        }
    }

    // Add similar methods for other custom queries
}

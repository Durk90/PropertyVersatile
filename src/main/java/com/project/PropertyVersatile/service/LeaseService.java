package com.project.PropertyVersatile.service;

import com.project.PropertyVersatile.entity.Lease;
import com.project.PropertyVersatile.repository.LeaseRepository;

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
public class LeaseService {

    private final LeaseRepository leaseRepository;
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public LeaseService(LeaseRepository leaseRepository) {
        this.leaseRepository = leaseRepository;
    }

    public List<Lease> getAllLeases() {
        try {
            return leaseRepository.findAll();
        } catch (Exception e) {
            // Log the exception
            logger.log(Level.SEVERE, "Error retrieving all leases", e);
            return Collections.emptyList();
        }
    }

    public Lease getLeaseById(int leaseId) {
        try {
            Optional<Lease> leaseOptional = leaseRepository.findById(leaseId);
            return leaseOptional.orElse(null);
        } catch (Exception e) {
            // Log the exception
            logger.log(Level.SEVERE, "Error retrieving lease by ID: " + leaseId, e);
            return null;
        }
    }

    public Lease createLease(Lease lease) {
        try {
            // Additional validation logic if needed
            return leaseRepository.save(lease);
        } catch (Exception e) {
            // Log the exception
            logger.log(Level.SEVERE, "Error creating lease", e);
            return null;
        }
    }

    public Lease updateLease(int leaseId, Lease updatedLease) {
        try {
            Optional<Lease> existingLeaseOptional = leaseRepository.findById(leaseId);
            return existingLeaseOptional.map(existingLease -> {
                // Update fields based on your requirements
                existingLease.setStartDate(updatedLease.getStartDate());
                existingLease.setEndDate(updatedLease.getEndDate());
                existingLease.setMonthlyRent(updatedLease.getMonthlyRent());
                existingLease.setTenantName(updatedLease.getTenantName());

                // Save the updated lease
                return leaseRepository.save(existingLease);
            }).orElse(null); // Return null if the lease with the given ID is not found
        } catch (Exception e) {
            // Log the exception
            logger.log(Level.SEVERE, "Error updating lease with ID: " + leaseId, e);
            return null;
        }
    }

    public boolean deleteLease(int leaseId) {
        try {
            leaseRepository.deleteById(leaseId);
            return true;
        } catch (Exception e) {
            // Log the exception
            logger.log(Level.SEVERE, "Error deleting lease with ID: " + leaseId, e);
            return false;
        }
    }
}

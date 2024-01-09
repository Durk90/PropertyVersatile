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
            logException("Error retrieving all leases", e);
            return Collections.emptyList();
        }
    }

    public Lease getLeaseById(int leaseId) {
        try {
            Optional<Lease> leaseOptional = leaseRepository.findById(leaseId);
            return leaseOptional.orElse(null);
        } catch (Exception e) {
            logException("Error retrieving lease by ID: " + leaseId, e);
            return null;
        }
    }

    public Lease createLease(Lease lease) {
        try {
            // Additional validation logic if needed
            return leaseRepository.save(lease);
        } catch (Exception e) {
            logException("Error creating lease", e);
            return null;
        }
    }

    public Lease updateLease(int leaseId, Lease updatedLease) {
        try {
            Optional<Lease> existingLeaseOptional = leaseRepository.findById(leaseId);
            return existingLeaseOptional.map(existingLease -> {
                updateLeaseFields(existingLease, updatedLease);
                return leaseRepository.save(existingLease);
            }).orElse(null);
        } catch (Exception e) {
            logException("Error updating lease with ID: " + leaseId, e);
            return null;
        }
    }

    public boolean deleteLease(int leaseId) {
        try {
            leaseRepository.deleteById(leaseId);
            return true;
        } catch (Exception e) {
            logException("Error deleting lease with ID: " + leaseId, e);
            return false;
        }
    }

    private void logException(String message, Exception e) {
        logger.log(Level.SEVERE, message, e);
    }

    private void updateLeaseFields(Lease existingLease, Lease updatedLease) {
        // Update fields based on your requirements
        existingLease.setstart_date(updatedLease.getstart_date());
        existingLease.setend_date(updatedLease.getend_date());
        existingLease.setMonthlyRent(updatedLease.getMonthlyRent());
        existingLease.setTenantName(updatedLease.getTenantName());
    }
}

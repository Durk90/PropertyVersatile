package com.project.PropertyVersatile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.PropertyVersatile.entity.Transaction;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    // Custom query to find transactions by property ID
    List<Transaction> findByPropertyId(int propertyId);

    // Custom query to find transactions by transaction date after a certain date
    List<Transaction> findByTransactionDateAfter(LocalDate transactionDate);

    // Custom query to find transactions with amount greater than a specified value
    List<Transaction> findByAmountGreaterThan(double amount);

    // Add more custom queries as needed

    // Handle error cases
    default List<Transaction> findByTransactionDateAfterSafe(LocalDate transactionDate) {
        try {
            return findByTransactionDateAfter(transactionDate);
        } catch (Exception e) {
            // Log the exception or handle it as needed
            e.printStackTrace();
            throw new RuntimeException("Error querying transactions by transaction date", e);
        }
    }

    // Add similar methods for other custom queries
}

package com.project.PropertyVersatile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.PropertyVersatile.entity.Transaction;
import com.project.PropertyVersatile.repository.TransactionRepository;
import java.util.Collections;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        try {
            return transactionRepository.findAll();
        } catch (Exception e) {
            // Log the exception
            logger.log(Level.SEVERE, "Error retrieving all transactions", e);
            return Collections.emptyList();
        }
    }

    public Transaction getTransactionById(int transactionId) {
        try {
            Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
            return transactionOptional.orElse(null);
        } catch (Exception e) {
            // Log the exception
            logger.log(Level.SEVERE, "Error retrieving transaction by ID: " + transactionId, e);
            return null;
        }
    }

    public Transaction createTransaction(Transaction transaction) {
        try {
            // Additional validation logic if needed
            return transactionRepository.save(transaction);
        } catch (Exception e) {
            // Log the exception
            logger.log(Level.SEVERE, "Error creating transaction", e);
            return null;
        }
    }

    public Transaction updateTransaction(int transactionId, Transaction updatedTransaction) {
        try {
            Optional<Transaction> existingTransactionOptional = transactionRepository.findById(transactionId);
            return existingTransactionOptional.map(existingTransaction -> {
                // Update the existing transaction with the new values
                existingTransaction.setPropertyId(updatedTransaction.getPropertyId());
                existingTransaction.setTransactionDate(updatedTransaction.getTransactionDate());
                existingTransaction.setAmount(updatedTransaction.getAmount());
                existingTransaction.setDescription(updatedTransaction.getDescription());
                existingTransaction.setTransactionType(updatedTransaction.getTransactionType());
                // Update other fields as needed
                return transactionRepository.save(existingTransaction);
            }).orElse(null); // Transaction not found
        } catch (Exception e) {
            // Log the exception
            logger.log(Level.SEVERE, "Error updating transaction with ID: " + transactionId, e);
            return null;
        }
    }

    public boolean deleteTransaction(int transactionId) {
        try {
            transactionRepository.deleteById(transactionId);
            return true;
        } catch (Exception e) {
            // Log the exception
            logger.log(Level.SEVERE, "Error deleting transaction with ID: " + transactionId, e);
            return false;
        }
    }
}

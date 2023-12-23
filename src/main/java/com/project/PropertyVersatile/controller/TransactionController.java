package com.project.PropertyVersatile.controller;

import com.project.PropertyVersatile.entity.Transaction;
import com.project.PropertyVersatile.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public String getAllTransactions(Model model) {
        try {
            List<Transaction> transactions = transactionService.getAllTransactions();
            model.addAttribute("transactions", transactions);
            return "financial-transactions"; // financial-transactions.html
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving transactions");
            return "error"; // error.html
        }
    }

    @GetMapping("/{transactionId}")
    public String getTransactionById(@PathVariable int transactionId, Model model) {
        try {
            Transaction transaction = transactionService.getTransactionById(transactionId);
            model.addAttribute("transaction", transaction);
            return "transaction-details"; // transaction-details.html
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving transaction details");
            return "error"; // error.html
        }
    }

    @PostMapping("/create")
    public String createTransaction(@ModelAttribute Transaction transaction, Model model) {
        try {
            transactionService.createTransaction(transaction);
            return "redirect:/transactions";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error creating transaction");
            return "error"; // error.html
        }
    }

    @GetMapping("/update/{transactionId}")
    public String showUpdateForm(@PathVariable int transactionId, Model model) {
        try {
            Transaction transaction = transactionService.getTransactionById(transactionId);
            model.addAttribute("transaction", transaction);
            return "update-transaction"; // update-transaction.html
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving transaction for updating");
            return "error"; // error.html
        }
    }

    @PostMapping("/update/{transactionId}")
    public String updateTransaction(@PathVariable int transactionId, @ModelAttribute Transaction updatedTransaction, Model model) {
        try {
            transactionService.updateTransaction(transactionId, updatedTransaction);
            return "redirect:/transactions";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error updating transaction");
            return "error"; // error.html
        }
    }

    @GetMapping("/delete/{transactionId}")
    public String deleteTransaction(@PathVariable int transactionId, Model model) {
        try {
            transactionService.deleteTransaction(transactionId);
            return "redirect:/transactions";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error deleting transaction");
            return "error"; // error.html
        }
    }
}

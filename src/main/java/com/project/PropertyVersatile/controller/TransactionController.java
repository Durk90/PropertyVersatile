package com.project.PropertyVersatile.controller;

import com.project.PropertyVersatile.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.project.PropertyVersatile.service.TransactionService;

import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionController {
	
	@GetMapping("/transactions")
    public String transactions() {
        return "transactions";
    }

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public String getAllTransactions(Model model) {
        List<Transaction> transactions = transactionService.getAllTransactions();
        model.addAttribute("transactions", transactions);
        return "financial-transactions";
    }

    @GetMapping("/{transactionId}")
    public String getTransactionById(@PathVariable int transactionId, Model model) {
        Transaction transaction = transactionService.getTransactionById(transactionId);
        model.addAttribute("transaction", transaction);
        return "transaction-details";
    }

    @PostMapping("/create")
    public String createTransaction(@ModelAttribute Transaction transaction) {
        transactionService.createTransaction(transaction);
        // Add logic to handle the result, e.g., redirect to a success page or show an error message
        return "redirect:/transactions";
    }

    @GetMapping("/update/{transactionId}")
    public String showUpdateForm(@PathVariable int transactionId, Model model) {
        Transaction transaction = transactionService.getTransactionById(transactionId);
        model.addAttribute("transaction", transaction);
        return "update-transaction";
    }

    @PostMapping("/update/{transactionId}")
    public String updateTransaction(@PathVariable int transactionId, @ModelAttribute Transaction updatedTransaction) {
        transactionService.updateTransaction(transactionId, updatedTransaction);
        // Add logic to handle the result, e.g., redirect to a success page or show an error message
        return "redirect:/transactions";
    }

    @GetMapping("/delete/{transactionId}")
    public String deleteTransaction(@PathVariable int transactionId) {
        transactionService.deleteTransaction(transactionId);
        // Add logic to handle the result, e.g., redirect to a success page or show an error message
        return "redirect:/transactions";
    }
}

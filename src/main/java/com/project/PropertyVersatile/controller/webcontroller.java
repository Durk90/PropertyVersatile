package com.project.PropertyVersatile.controller;

import com.project.PropertyVersatile.entity.Lease;
import com.project.PropertyVersatile.entity.Maintenance;
import com.project.PropertyVersatile.entity.Property;
import com.project.PropertyVersatile.entity.Transaction;
import com.project.PropertyVersatile.service.LeaseService;
import com.project.PropertyVersatile.service.MaintenanceService;
import com.project.PropertyVersatile.service.PropertyService;
import com.project.PropertyVersatile.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class webcontroller {

    @Autowired
    private LeaseService leaseService;

    @Autowired
    private MaintenanceService maintenanceService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/web/leases")
    public String getAllWebLeases(Model model) {
        try {
            List<Lease> leases = leaseService.getAllLeases();
            model.addAttribute("leases", leases);
            return "leases"; // The name of your HTML page (e.g., leases.html)
        } catch (Exception e) {
            // Log the exception or handle it as needed
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving leases");
            return "error"; // Create an error.html template to display error messages
        }
    }

    @GetMapping("/web/maintenance")
    public String getAllMaintenance(Model model) {
        List<Maintenance> maintenanceRequests = maintenanceService.getAllMaintenance();
        model.addAttribute("maintenanceRequests", maintenanceRequests);
        return "maintenance"; // The name of your HTML page (e.g., maintenance-requests.html)
    }

    @GetMapping("/web/properties")
    public String getAllWebProperties(Model model) {
        try {
            List<Property> properties = propertyService.getAllProperties();
            model.addAttribute("properties", properties);
            return "properties"; // The name of your HTML page (e.g., properties.html)
        } catch (Exception e) {
            // Log the exception or handle it as needed
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving properties");
            return "error"; // Create an error.html template to display error messages
        }
    }

    @GetMapping("/web/transactions")
    public String getAllTransactions(Model model) {
        List<Transaction> transactions = transactionService.getAllTransactions();
        model.addAttribute("transactions", transactions);
        return "financial-transactions"; // The name of your HTML page (e.g., financial-transactions.html)
    }
}

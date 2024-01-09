package com.project.PropertyVersatile.controller;

import com.project.PropertyVersatile.entity.Lease;
import com.project.PropertyVersatile.service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/leases")
public class LeaseController {

    private final LeaseService leaseService;

    @Autowired
    public LeaseController(LeaseService leaseService) {
        this.leaseService = leaseService;
    }

    @GetMapping
    public String getAllLeases(Model model) {
        try {
            List<Lease> leases = leaseService.getAllLeases();
            model.addAttribute("leases", leases);
            return "leases";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving leases");
            return "error";
        }
    }

    @GetMapping("/{leaseId}")
    public String getLeaseById(@PathVariable int leaseId, Model model) {
        try {
            Lease lease = leaseService.getLeaseById(leaseId);
            model.addAttribute("lease", lease);
            return "lease-details";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving lease details");
            return "error";
        }
    }

    @GetMapping("/create")
    public String showCreateLeaseForm(Model model) {
        model.addAttribute("lease", new Lease());
        model.addAttribute("action", "create"); // Set action to 'create'
        return "create-lease";
    }

    @PostMapping("/create")
    public String createLease(@ModelAttribute Lease lease, Model model) {
        try {
            leaseService.createLease(lease);
            return "redirect:/leases";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error creating lease. Please check your input.");
            return "error";
        }
    }

    @GetMapping("/{leaseId}/edit")
    public String showEditLeaseForm(@PathVariable int leaseId, Model model) {
        try {
            Lease lease = leaseService.getLeaseById(leaseId);
            model.addAttribute("lease", lease);
            model.addAttribute("action", "edit"); // Set action to 'edit'
            return "edit-lease";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving lease details for editing");
            return "error";
        }
    }

    @PostMapping("/{leaseId}/edit")
    public String updateLease(@PathVariable int leaseId, @ModelAttribute Lease updatedLease, Model model) {
        try {
            leaseService.updateLease(leaseId, updatedLease);
            return "redirect:/leases";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error updating lease. Please check your input.");
            return "error";
        }
    }

    @GetMapping("/{leaseId}/delete")
    public String deleteLease(@PathVariable int leaseId, Model model) {
        try {
            leaseService.deleteLease(leaseId);
            // Additional logic for deleting lease
            model.addAttribute("action", "delete"); // Set action to 'delete'
            return "leases";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error deleting lease");
            return "error";
        }
    }
}

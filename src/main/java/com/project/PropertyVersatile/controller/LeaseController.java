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

    @GetMapping("/")
    public String getAllLeases(Model model) {
        try {
            List<Lease> leases = leaseService.getAllLeases();
            model.addAttribute("leases", leases);
            return "leases"; // leases.html
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving leases");
            return "error"; // error.html
        }
    }

    @GetMapping("/{leaseId}")
    public String getLeaseById(@PathVariable int leaseId, Model model) {
        try {
            Lease lease = leaseService.getLeaseById(leaseId);
            model.addAttribute("lease", lease);
            return "lease-details"; // lease-details.html
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving lease details");
            return "error"; // error.html
        }
    }

    @GetMapping("/create")
    public String showCreateLeaseForm(Model model) {
        model.addAttribute("lease", new Lease());
        return "create-lease"; // create-lease.html
    }

    @PostMapping("/create")
    public String createLease(@ModelAttribute Lease lease, Model model) {
        try {
            leaseService.createLease(lease);
            return "redirect:/leases";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error creating lease");
            return "error"; // error.html
        }
    }

    @GetMapping("/{leaseId}/edit")
    public String showEditLeaseForm(@PathVariable int leaseId, Model model) {
        try {
            Lease lease = leaseService.getLeaseById(leaseId);
            model.addAttribute("lease", lease);
            return "edit-lease"; // edit-lease.html
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving lease for editing");
            return "error"; // error.html
        }
    }

    @PostMapping("/{leaseId}/edit")
    public String editLease(@PathVariable int leaseId, @ModelAttribute Lease updatedLease, Model model) {
        try {
            leaseService.updateLease(leaseId, updatedLease);
            return "redirect:/leases";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error editing lease");
            return "error"; // error.html
        }
    }

    @PostMapping("/{leaseId}/delete")
    public String deleteLease(@PathVariable int leaseId, Model model) {
        try {
            leaseService.deleteLease(leaseId);
            return "redirect:/leases";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error deleting lease");
            return "error"; // error.html
        }
    }

    // Additional methods for creating, updating, and deleting leases if needed
}

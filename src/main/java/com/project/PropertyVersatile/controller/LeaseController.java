package com.project.PropertyVersatile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.PropertyVersatile.entity.Lease;
import com.project.PropertyVersatile.service.LeaseService;

import java.util.List;

@Controller
@RequestMapping("/leases")
public class LeaseController {
	
	@GetMapping("/leases")
    public String leases() {
        return "leases";
    }

    private final LeaseService leaseService;

    @Autowired
    public LeaseController(LeaseService leaseService) {
        this.leaseService = leaseService;
    }

    @GetMapping
    public String getAllLeases(Model model) {
        List<Lease> leases = leaseService.getAllLeases();
        model.addAttribute("leases", leases);
        return "leases";
    }

    @GetMapping("/{leaseId}")
    public String getLeaseById(@PathVariable int leaseId, Model model) {
        Lease lease = leaseService.getLeaseById(leaseId);
        model.addAttribute("lease", lease);
        return "lease-details";
    }

    @PostMapping("/create")
    public String createLease(@ModelAttribute Lease lease) {
        leaseService.createLease(lease);
        return "redirect:/leases";
    }

    @PutMapping("/{leaseId}")
    public String updateLease(@PathVariable int leaseId, @ModelAttribute Lease updatedLease) {
        leaseService.updateLease(leaseId, updatedLease);
        return "redirect:/leases";
    }

    @DeleteMapping("/{leaseId}")
    public String deleteLease(@PathVariable int leaseId) {
        leaseService.deleteLease(leaseId);
        return "redirect:/leases";
    }

    // Additional methods for creating, updating, and deleting leases if needed
}

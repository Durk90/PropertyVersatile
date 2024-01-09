package com.project.PropertyVersatile.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "leases")
public class Lease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lease_id")
    private int leaseId;

    @Column(name = "property_id")
    private int propertyId;

    @ManyToOne(fetch = FetchType.LAZY) // Many leases can be associated with one property
    @JoinColumn(name = "property_id", referencedColumnName = "property_id", insertable = false, updatable = false)
    private Property property; // Add a Property field

    @Column(name = "start_date")
    private LocalDate start_date;

    @Column(name = "end_date")
    private LocalDate end_date;

    @Column(name = "monthly_rent")
    private BigDecimal monthlyRent;

    @Column(name = "tenant_name")
    private String tenantName;

    // Constructors

    // Default constructor
    public Lease() {
    }

    // Parameterized constructor
    public Lease(int propertyId, LocalDate start_date, LocalDate end_date, BigDecimal monthlyRent, String tenantName) {
        this.propertyId = propertyId;
        this.start_date = start_date;
        this.end_date = end_date;
        this.monthlyRent = monthlyRent;
        this.tenantName = tenantName;
    }

    // Getters and setters

    public int getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(int leaseId) {
        this.leaseId = leaseId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public LocalDate getstart_date() {
        return start_date;
    }

    public void setstart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getend_date() {
        return end_date;
    }

    public void setend_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public BigDecimal getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(BigDecimal monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }
}

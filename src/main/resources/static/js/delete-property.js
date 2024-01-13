function confirmDelete(propertyId) {
    // Check for associated maintenance requests
    // Access the hasMaintenanceRequests variable set in the HTML
    var hasMaintenanceRequests = window.hasMaintenanceRequests;

    // Display warning and confirm cancellation
    if (hasMaintenanceRequests) {
        alert("Warning: This property has associated maintenance requests. Deleting it will also delete the associated maintenance requests. You can only cancel the operation.");
        return false; // Cancel the form submission
    } else {
        // Confirm deletion
        return confirm("Are you sure you want to delete this property?");
    }
}

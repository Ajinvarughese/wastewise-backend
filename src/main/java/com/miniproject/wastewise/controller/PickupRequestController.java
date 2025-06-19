package com.miniproject.wastewise.controller;

import com.miniproject.wastewise.entities.PickupRequest;
import com.miniproject.wastewise.enums.PickupStatus;
import com.miniproject.wastewise.services.PickupRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/pickup")
@CrossOrigin(origins = "*")
public class PickupRequestController {

    private final PickupRequestService pickupService;

    public PickupRequestController(PickupRequestService pickupService) {
        this.pickupService = pickupService;
    }

    // 1. Create pickup request
    @PostMapping
    public PickupRequest createRequest(@RequestBody PickupRequest request) {
        return pickupService.createRequest(request);
    }

    // 2. Get all pickup requests (for admin/driver)
    @GetMapping
    public List<PickupRequest> getAllRequests() {
        return pickupService.getAllRequests();
    }

    // 3. Get pickup requests by email (for resident)
    @GetMapping("/user/{email}")
    public List<PickupRequest> getRequestsByEmail(@PathVariable String email) {
        return pickupService.getRequestsByEmail(email);
    }

    @GetMapping("/driver/{id}")
    public ResponseEntity<List<PickupRequest>> getPickupByDriver(@PathVariable Long id) {
        return ResponseEntity.ok(pickupService.getPickupByDriver(id));
    }

    // 4. Update status (admin/driver marks as completed)
    @PutMapping("/status")
    public PickupRequest updateStatus(@RequestBody PickupRequest pickupRequest) {
        return pickupService.updateStatus(pickupRequest);
    }

    @PutMapping("/schedule")
    public ResponseEntity<PickupRequest> updateTime(@RequestBody PickupRequest pickupRequest) {
        return ResponseEntity.ok(pickupService.updateTime(pickupRequest));
    }

    @GetMapping("/zipcode/{code}")
    public ResponseEntity<List<PickupRequest>> getRequestByZip(@PathVariable Long code) {
        return ResponseEntity.ok(pickupService.getPickupByZip(code));
    }

    // 5. Delete request (optional - admin cleanup)
    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable Long id) {
        pickupService.deleteRequest(id);
    }
}
package com.miniproject.wastewise.services;


import com.miniproject.wastewise.entities.PickupRequest;
import com.miniproject.wastewise.enums.PickupStatus;
import com.miniproject.wastewise.repository.PickupRequestRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PickupRequestService {

    private final PickupRequestRepository pickupRepo;

    public PickupRequestService(PickupRequestRepository pickupRepo) {
        this.pickupRepo = pickupRepo;
    }

    // Create new request
    public PickupRequest createRequest(PickupRequest request) {
        request.setStatus(PickupStatus.PENDING);
        request.setScheduledTime("9:00AM - 5:00PM");
        return pickupRepo.save(request);
    }

    // Get all requests
    public List<PickupRequest> getAllRequests() {
        return pickupRepo.findAll();
    }

    // Get by resident email
    public List<PickupRequest> getRequestsByEmail(String email) {
        return pickupRepo.findByUserEmail(email);
    }

    // Update status (for driver/admin)
    public PickupRequest updateStatus(PickupRequest pickupRequest) {
        Optional<PickupRequest> optional = pickupRepo.findById(pickupRequest.getId());
        if (optional.isPresent()) {
            PickupRequest request = optional.get();
            if(pickupRequest.getStatus().equals(PickupStatus.ACCEPTED)) {
                request.setDriver(pickupRequest.getDriver());
            } if(pickupRequest.getStatus().equals(PickupStatus.PENDING)) {
                request.setDriver(null);
            }
            request.setStatus(pickupRequest.getStatus());
            return pickupRepo.save(request);
        }
        return null;
    }

    public List<PickupRequest> getPickupByZip(Long code) {
        return pickupRepo.findByUserZipCode(code);
    }

    public List<PickupRequest> getPickupByDriver(Long id) {
        return pickupRepo.findByDriverId(id);
    }

    // Delete request (optional)
    public void deleteRequest(Long id) {
        pickupRepo.deleteById(id);
    }

    public PickupRequest updateTime(PickupRequest pickupRequest) {
        PickupRequest existing = pickupRepo.findById(pickupRequest.getId())
                .orElseThrow(() -> new EntityNotFoundException("Pickup not found with id: "+pickupRequest.getId()));
        existing.setScheduledTime(pickupRequest.getScheduledTime());
        existing.setStatus(PickupStatus.ACCEPTED);
        return pickupRepo.save(existing);
    }
}

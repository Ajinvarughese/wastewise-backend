package com.miniproject.wastewise.repository;

import com.miniproject.wastewise.entities.PickupRequest;
import com.miniproject.wastewise.enums.PickupStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PickupRequestRepository extends JpaRepository<PickupRequest, Long> {
    List<PickupRequest> findByUserEmail(String email);
    List<PickupRequest> findByStatus(PickupStatus status);
    List<PickupRequest> findByDriverId(Long id);
    List<PickupRequest> findByUserZipCode(Long zipCode);
}

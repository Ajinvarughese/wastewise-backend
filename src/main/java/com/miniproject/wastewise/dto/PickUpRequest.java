package com.miniproject.wastewise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PickUpRequest {
    private String pickupDate;
    private String wasteType;
    private String notes;
}


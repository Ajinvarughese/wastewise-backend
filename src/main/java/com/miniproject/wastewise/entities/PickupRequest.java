package com.miniproject.wastewise.entities;

import com.miniproject.wastewise.enums.PickupStatus;
import com.miniproject.wastewise.enums.WasteTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PickupRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private User driver;

    private String scheduledTime;
    private LocalDate pickupDate;
    @Enumerated(EnumType.STRING)
    private WasteTypes wasteType;
    private String notes;

    @Enumerated(EnumType.STRING)
    private PickupStatus status;
}

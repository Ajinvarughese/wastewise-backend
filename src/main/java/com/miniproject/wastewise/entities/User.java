package com.miniproject.wastewise.entities;

import com.miniproject.wastewise.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String fullName;

        @Column(unique = true)
        private String email;

        private String password;

        @Column(unique = true)
        private String phone;

        private String address;
        private Long zipCode;

        private LocalDate dob;

        @Enumerated(EnumType.STRING)
        private Role role;

        @CreationTimestamp
        private LocalDate createdAt;

        private String profileUrl;
}







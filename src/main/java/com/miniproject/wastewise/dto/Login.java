package com.miniproject.wastewise.dto;

import com.miniproject.wastewise.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Login {
    private String email;
    private String password;
    private Role role;
}


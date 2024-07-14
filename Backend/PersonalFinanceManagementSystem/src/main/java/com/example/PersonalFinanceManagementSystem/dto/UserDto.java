package com.example.PersonalFinanceManagementSystem.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String email;
}

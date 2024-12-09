package com.personalfinancial.dto;

import com.personalfinancial.entities.UserRole;

public record RegisterDTO(String email, String password, UserRole role) {

}

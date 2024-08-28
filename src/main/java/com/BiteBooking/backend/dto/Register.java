package com.BiteBooking.backend.dto;

import java.time.LocalDate;
import com.BiteBooking.backend.model.Role;

public record Register(
        String email,
        String password,
        String firstName,
        String lastName,
        LocalDate birthdayDate,
        String phone,
        Role role,
        String imgUser
) {
}

package com.BiteBooking.backend.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdayDate;
    private String email;
    private String password;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String imgUser;
    private String city;
    private String aboutMe;
}

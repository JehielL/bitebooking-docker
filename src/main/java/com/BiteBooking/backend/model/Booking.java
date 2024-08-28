package com.BiteBooking.backend.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "booking")
public class Booking {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private LocalDateTime createDate;
    @ManyToOne
    private User user;
    private Integer numUsers;
    private String observations;
    private Boolean status;
    private Boolean interior;
    private Integer numTable;
    @ManyToOne()
    private Restaurant restaurant;
    private String extraService;


}

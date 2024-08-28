package com.BiteBooking.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "menu")
public class Menu {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String title;
    private String description;
    private String imgMenu;
    private Boolean active;
    @Enumerated(EnumType.STRING)
    private RestaurantType restaurantType;
    private Boolean alergys;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;



}

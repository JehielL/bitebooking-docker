package com.BiteBooking.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "dish")
public class Dish {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imgDish;
    private Boolean active;
    private Boolean alergys;
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

}

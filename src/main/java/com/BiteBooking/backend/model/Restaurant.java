package com.BiteBooking.backend.model;
import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Restaurants")
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    private RestaurantType restaurantType;

    private String description;

    private LocalTime openingTime;

    private LocalTime closingTime;

    private Boolean status;

    private String imageUrl;

    private String city;

    private String address;

    private String number;

    private String postalCode;

    private Double averageRating;

    private Integer discount;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User owner;
}
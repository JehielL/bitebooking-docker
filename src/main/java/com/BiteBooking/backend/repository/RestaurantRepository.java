package com.BiteBooking.backend.repository;

import com.BiteBooking.backend.model.Menu;
import com.BiteBooking.backend.model.Restaurant;
import com.BiteBooking.backend.model.RestaurantType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByNameContainingIgnoreCase(String name);

    List<Restaurant> findByRestaurantType(RestaurantType restaurantType);

    List<Restaurant> findByOwnerId(Long owner);

    boolean existsByOwner_Id(Long id);

    boolean existsByOwner_IdAndId(Long id, Long id1);


}
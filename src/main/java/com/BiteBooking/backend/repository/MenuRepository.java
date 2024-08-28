package com.BiteBooking.backend.repository;

import com.BiteBooking.backend.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByRestaurantId(Long restaurantId);
    boolean existsByIdAndRestaurantOwnerId(Long menuId, Long ownerId);

}
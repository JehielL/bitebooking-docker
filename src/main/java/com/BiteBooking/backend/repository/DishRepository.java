package com.BiteBooking.backend.repository;

import com.BiteBooking.backend.model.Booking;
import com.BiteBooking.backend.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findAllByMenu_Id(Long id);
}
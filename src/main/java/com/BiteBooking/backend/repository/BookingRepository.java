package com.BiteBooking.backend.repository;

import com.BiteBooking.backend.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {


    List<Booking> findAllByRestaurant_Id(Long id);


    List<Booking> findAllByUserId(Long id);
}
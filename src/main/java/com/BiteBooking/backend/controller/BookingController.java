package com.BiteBooking.backend.controller;
import com.BiteBooking.backend.model.Booking;
import com.BiteBooking.backend.repository.BookingRepository;
import com.BiteBooking.backend.security.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;

@CrossOrigin("*")
@RestController
@AllArgsConstructor


public class BookingController {

    private final Logger log = LoggerFactory.getLogger(BookingController.class);
    private final BookingRepository bookingRepository;


    @GetMapping("bookings")
    public List<Booking> findAll() {
        log.info("REST request to findAll Bookings");
        SecurityUtils.getCurrentUser().ifPresent(System.out::println);
        return this.bookingRepository.findAll();
    }

    @GetMapping("bookings/{id}")
    public ResponseEntity<Booking> findById(@PathVariable Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            return ResponseEntity.ok(optionalBooking.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("bookings/filter-by-restaurant/{id}")
    public List<Booking> findAllByRestaurantId(@PathVariable Long id){
        return this.bookingRepository.findAllByRestaurant_Id(id);
    }

    @GetMapping("bookings/filter-by-user/{id}")
    public List<Booking> findAllByUserId(@PathVariable Long id){
        return this.bookingRepository.findAllByUserId(id);
    }

    @PostMapping("bookings")
    public Booking create(@RequestBody Booking booking) {
        SecurityUtils.getCurrentUser().ifPresent(user -> booking.setUser(user));

        return this.bookingRepository.save(booking);
    }
    @PutMapping("bookings/{id}")
    public ResponseEntity<Booking> update(@RequestBody Booking booking) {
        if (booking.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Booking savedBooking = bookingRepository.save(booking);
        return ResponseEntity.ok(booking);

    }
    @DeleteMapping("bookings/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            bookingRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Ocurrio un Error al eliminar su reserva nro: {}:{}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}





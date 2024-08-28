package com.BiteBooking.backend.controller;

import com.BiteBooking.backend.exception.UnauthorizedException;
import com.BiteBooking.backend.model.Rating;
import com.BiteBooking.backend.model.Role;
import com.BiteBooking.backend.model.User;
import com.BiteBooking.backend.repository.RatingRepository;
import com.BiteBooking.backend.security.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@AllArgsConstructor
@CrossOrigin("*")
public class RatingController {

    private final RatingRepository ratingRepository;


    @GetMapping("ratings/{id}")
    public Rating findById(@PathVariable Long id) {

        return this.ratingRepository.findById(id).orElseThrow();
    }

    @GetMapping("menus/filter-by-menu/{id}")
    public List<Rating> findAllByMenuId(@PathVariable Long id) {
        return this.ratingRepository.findByMenu_IdOrderByIdDesc(id);
    }


    @PostMapping("ratings")
    public Rating create(@RequestBody Rating rating) {


        SecurityUtils.getCurrentUser().ifPresent(user -> rating.setUser(user));
        return this.ratingRepository.save(rating);
    }

    @PutMapping("ratings/{id}")
    public Rating update(@PathVariable Long id, @RequestBody Rating rating) {
        if (this.ratingRepository.existsById(id))
            return this.ratingRepository.save(rating);

        throw new NoSuchElementException();

    }

    @DeleteMapping("ratings/{id}")
    public void deleteById(@PathVariable Long id) {

        Rating rating = this.ratingRepository.findById(id).orElseThrow();
        User user = SecurityUtils.getCurrentUser().orElseThrow();

        if(user.getRole().equals(Role.ADMIN) ||
                (rating.getUser() != null && rating.getUser().getId().equals(user.getId()))
        )
            this.ratingRepository.deleteById(id);

        else
            throw new UnauthorizedException("No puede borrar el rating");


    }


}
package com.BiteBooking.backend.repository;

import com.BiteBooking.backend.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public interface RatingRepository extends JpaRepository<Rating, Long> {
    //List<Rating> findByRestaurantId(Long id);

   // List<Rating> findRatingsByRestaurantId(Long id);

    List<Rating> findByMenu_IdOrderByIdDesc(Long id);



    @Transactional
    @Modifying
    void deleteByMenuId(Long menuId);
}


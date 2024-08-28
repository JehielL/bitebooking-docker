package com.BiteBooking.backend.repository;

import com.BiteBooking.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    Optional<User> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
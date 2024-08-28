package com.BiteBooking.backend.controller;

import com.BiteBooking.backend.model.Booking;
import com.BiteBooking.backend.model.Dish;
import com.BiteBooking.backend.model.Menu;
import com.BiteBooking.backend.repository.DishRepository;
import com.BiteBooking.backend.service.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@Slf4j
public class DishController {


    private final DishRepository dishRepository;
    private FileService fileService;



    @GetMapping("dishes")
    public ResponseEntity<List<Dish>> findAll() {
        List<Dish> dishes = dishRepository.findAll();
        if (dishes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dishes);
    }

    @GetMapping("dishes/{id}")
    public ResponseEntity<Dish> findById(@PathVariable Long id) {
        Optional<Dish> optionalDish = dishRepository.findById(id);
        if (optionalDish.isPresent()) {
            return ResponseEntity.ok(optionalDish.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("dishes/filter-by-menu/{id}")
    public List<Dish> findAllByMenuId(@PathVariable Long id){
        return this.dishRepository.findAllByMenu_Id(id);
    }


    @PostMapping("dishes")
    public Dish create(
            @RequestParam(value = "photo", required = false) MultipartFile file,
            Dish dish){

        if(file != null && !file.isEmpty()) {
            String fileName = fileService.store(file);
            dish.setImgDish(fileName);
        } else {
            dish.setImgDish("avatar.png");
        }

        return this.dishRepository.save(dish);
    }
    @PutMapping("dishes/{id}")
    public ResponseEntity<Dish> update(
            @PathVariable Long id,
            Dish dish,
            @RequestParam(value = "photo", required = false) MultipartFile file
    ){
        if(!this.dishRepository.existsById(id))
            return ResponseEntity.notFound().build();

        if(file != null && !file.isEmpty()) {
            String fileName = fileService.store(file);
            dish.setImgDish(fileName);
        }
        return ResponseEntity.ok(this.dishRepository.save(dish));
    }
    @DeleteMapping("dishes/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            dishRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Ocurrio un Error al eliminar su reserva nro: {}:{}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

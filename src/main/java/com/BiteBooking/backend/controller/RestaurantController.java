package com.BiteBooking.backend.controller;

import com.BiteBooking.backend.exception.UnauthorizedException;
import com.BiteBooking.backend.model.Restaurant;
import com.BiteBooking.backend.model.RestaurantType;
import com.BiteBooking.backend.model.Role;
import com.BiteBooking.backend.model.User;
import com.BiteBooking.backend.repository.RestaurantRepository;
import com.BiteBooking.backend.security.SecurityUtils;
import com.BiteBooking.backend.service.FileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@CrossOrigin("*")
@Slf4j
@AllArgsConstructor
@RestController
public class RestaurantController {
    private final RestaurantRepository repository;
    private FileService fileService;

    @GetMapping("/my-restaurants")
    public ResponseEntity<List<Restaurant>> getMyRestaurants() {
        User currentUser = SecurityUtils.getCurrentUser().orElseThrow(() -> new RuntimeException("No autenticado"));
        List<Restaurant> myRestaurants = repository.findByOwnerId(currentUser.getId());
        return ResponseEntity.ok(myRestaurants);
    }

    @GetMapping("restaurants/can-edit/{id}")
    public ResponseEntity<Boolean> canEditRestaurant(@PathVariable Long id) {
        User currentUser = SecurityUtils.getCurrentUser().orElseThrow(() -> new RuntimeException("No autenticado"));
        boolean canEdit = repository.existsByOwner_IdAndId(currentUser.getId(), id);

        if(currentUser.getRole() == Role.ADMIN || canEdit)
            return ResponseEntity.ok(true);
        else
            return ResponseEntity.status(HttpStatusCode.valueOf(403)).build();
    }

    @GetMapping("restaurant-list/{restaurantType}")
    public List<Restaurant> findByRestaurantType(@PathVariable RestaurantType restaurantType) {
        return repository.findByRestaurantType(restaurantType);
    }

    @GetMapping("/restaurant")
    public ResponseEntity<List<Restaurant>> findAll(@RequestParam(required = false) String name) {
        List<Restaurant> restaurants;
        if (name != null && !name.isEmpty()) {
            restaurants = repository.findByNameContainingIgnoreCase(name);
        } else {
            restaurants = repository.findAll();
           // restaurants = repository.findAllByActiveTrue();
        }
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(restaurants);
    }
    @GetMapping("/restaurant/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable  Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/restaurant")
    public Restaurant create(@RequestParam(value = "photo", required = false) MultipartFile file,
                             @RequestParam MultiValueMap<String, String> formData) {
        LocalTime openingTime = LocalTime.parse(Objects.requireNonNull(formData.getFirst("openingTime")));
        LocalTime closingTime = LocalTime.parse(Objects.requireNonNull(formData.getFirst("closingTime")));
        Restaurant restaurant = new Restaurant();
        restaurant.setName(formData.getFirst("name"));
        restaurant.setPhone(formData.getFirst("phone"));
        String restaurantTypeStr = formData.getFirst("restaurantType");
        RestaurantType restaurantType = null;
        try {
            restaurantType = RestaurantType.valueOf(restaurantTypeStr);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new RuntimeException("Tipo de restaurante inválido o nulo: " + restaurantTypeStr);
        }
        restaurant.setRestaurantType(restaurantType);
        restaurant.setDescription(formData.getFirst("description"));
        restaurant.setCity(formData.getFirst("city"));
        restaurant.setAddress(formData.getFirst("address"));
        restaurant.setNumber(formData.getFirst("number"));
        restaurant.setPostalCode(formData.getFirst("postalCode"));

        if (file != null && !file.isEmpty()) {
            String fileName = fileService.store(file);
            restaurant.setImageUrl(fileName);
        }

        restaurant.setOpeningTime(openingTime);
        restaurant.setClosingTime(closingTime);

        return this.repository.save(restaurant);
    }



    @PutMapping("/restaurant/{id}")
    public Restaurant updateRestaurant(@PathVariable Long id,
                                       @RequestParam(value = "photo", required = false) MultipartFile file,
                                       @RequestParam MultiValueMap<String, String> formData) {

        User currentUser = SecurityUtils.getCurrentUser().orElseThrow(() -> new RuntimeException("No autenticado"));
        boolean canEdit = repository.existsByOwner_IdAndId(currentUser.getId(), id);
        if (!(currentUser.getRole() == Role.ADMIN || canEdit)) {
            throw new UnauthorizedException("No puede editar");
        }

        // Busca el restaurante existente por ID en lugar de crear uno nuevo
        Restaurant restaurant = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Restaurante no encontrado con id: " + id));

        // Actualiza los campos del restaurante existente
        restaurant.setName(formData.getFirst("name"));
        restaurant.setPhone(formData.getFirst("phone"));
        restaurant.setRestaurantType(RestaurantType.valueOf(formData.getFirst("restaurantType")));
        restaurant.setDescription(formData.getFirst("description"));
        restaurant.setCity(formData.getFirst("city"));
        restaurant.setAddress(formData.getFirst("address"));
        restaurant.setNumber(formData.getFirst("number"));
        restaurant.setPostalCode(formData.getFirst("postalCode"));
        LocalTime openingTime = LocalTime.parse(Objects.requireNonNull(formData.getFirst("openingTime")));
        restaurant.setOpeningTime(openingTime);
        LocalTime closingTime = LocalTime.parse(Objects.requireNonNull(formData.getFirst("closingTime")));
        restaurant.setClosingTime(closingTime);

        if (file != null && !file.isEmpty()) {
            String fileName = fileService.store(file);
            restaurant.setImageUrl(fileName);
        }

        // Guarda y devuelve el restaurante actualizado
        return repository.save(restaurant);
    }



    @DeleteMapping("/restaurant/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/restaurant/filter")
    public ResponseEntity<List<Restaurant>> findAllFiltering(@RequestBody Restaurant restaurant) {
        Example<Restaurant> filter = Example.of(restaurant);
        List<Restaurant> restaurants = repository.findAll(filter);
        if (restaurants.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(restaurants);
    }
}
package com.BiteBooking.backend.controller;
import com.BiteBooking.backend.model.Menu;
import com.BiteBooking.backend.model.Role;
import com.BiteBooking.backend.model.User;
import com.BiteBooking.backend.repository.MenuRepository;
import com.BiteBooking.backend.repository.RatingRepository;
import com.BiteBooking.backend.security.SecurityUtils;
import com.BiteBooking.backend.service.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@Slf4j

public class MenuController {

    private final MenuRepository menuRepository;
    private FileService fileService;
    private RatingRepository ratingRepository;

    @GetMapping("/menus/can-edit/{menuId}")
    public ResponseEntity<Boolean> canEditMenu(@PathVariable Long menuId) {
        User currentUser = SecurityUtils.getCurrentUser().orElseThrow(() -> new RuntimeException("No autenticado"));
        boolean canEdit = menuRepository.existsByIdAndRestaurantOwnerId(menuId, currentUser.getId());

        if(currentUser.getRole() == Role.ADMIN || canEdit) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }



    @GetMapping("/menus/byRestaurant/{restaurantId}")
    public ResponseEntity<List<Menu>> findByRestaurant(@PathVariable Long restaurantId) {
        List<Menu> menus = menuRepository.findByRestaurantId(restaurantId);
        if (menus.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(menus);
    }

    @GetMapping("menus")
    public List<Menu> findAll(){
        return menuRepository.findAll();
    }
    @GetMapping("menus/{id}")
    public ResponseEntity<Menu> findById(@PathVariable Long id) {
        Optional<Menu> optionalMenu = menuRepository.findById(id);
        if (optionalMenu.isPresent()) {
            return ResponseEntity.ok(optionalMenu.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("menus")
    public Menu create(@RequestParam(value = "photo", required = false ) MultipartFile file, Menu menu){

        if (file != null){
            String fileName = fileService.store(file);
            menu.setImgMenu(fileName);

        } else {
            menu.setImgMenu("avatar.png");
        }

        return this.menuRepository.save(menu);
    }

    @PutMapping("menus/{id}")
    public ResponseEntity<Menu> update(
            @PathVariable Long id,
            Menu menu,
            @RequestParam(value = "photo", required = false) MultipartFile file
    ){
        if(!this.menuRepository.existsById(id))
            return ResponseEntity.notFound().build();

        if(file != null && !file.isEmpty()) {
            String fileName = fileService.store(file);
            menu.setImgMenu(fileName);
        }
        return ResponseEntity.ok(this.menuRepository.save(menu));
    }

    @DeleteMapping("menus/{id}")
    public void deleteById(@PathVariable Long id){


        this.ratingRepository.deleteByMenuId(id);
        this.menuRepository.deleteById(id);
    }

}


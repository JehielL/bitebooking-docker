package com.BiteBooking.backend.controller;

import com.BiteBooking.backend.dto.Login;
import com.BiteBooking.backend.dto.Register;
import com.BiteBooking.backend.dto.Token;
import com.BiteBooking.backend.model.Dish;
import com.BiteBooking.backend.model.Role;
import com.BiteBooking.backend.model.User;
import com.BiteBooking.backend.repository.UserRepository;
import com.BiteBooking.backend.security.SecurityUtils;
import com.BiteBooking.backend.service.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.parsers.ReturnTypeParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Base64;
import java.util.*;
import java.util.concurrent.TimeUnit;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;


@CrossOrigin("*")
@Slf4j
@AllArgsConstructor
@RestController
public class UserController {
    private final FileService fileService;
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("user")
    public List<User> findAll(){

        return userRepository.findAll();
    }

    @GetMapping("users/account")
    public User getCurrentUser(){
        return SecurityUtils.getCurrentUser().orElseThrow();
    }


    @GetMapping("user/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        Optional<User> users = userRepository.findById(id);

        if (users.isPresent()){
            return ResponseEntity.ok(users.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/users/register")
    public void register(@RequestBody Register register) {
        if (this.userRepository.existsByEmail(register.email())) {
            throw new RuntimeException("Email ocupado");
        }
        User user = User.builder()
                .email(register.email())
                .password(passwordEncoder.encode(register.password()))
                .firstName(register.firstName())
                .lastName(register.lastName())
                .birthdayDate(register.birthdayDate())
                .phone(register.phone())
                .role(register.role()).role(Role.USER)
                .imgUser(register.imgUser()).imgUser("https://www.pngkey.com/png/detail/230-2301779_best-classified-apps-default-user-profile.png")
                .build();
        this.userRepository.save(user);
    }


    @PostMapping("/users/login")
    public Token login(@RequestBody Login login) {
        SecurityUtils.getCurrentUser().ifPresent(System.out::println);

        if (!userRepository.existsByEmail(login.email())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        User user = userRepository.findByEmail(login.email()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        if(!passwordEncoder.matches(login.password(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales incorrectas");
        }
        Date issuedDate = new Date();
        long nextWeekMillis = TimeUnit.DAYS.toMillis(7);
        Date expirationDate = new Date(issuedDate.getTime() + nextWeekMillis);

        byte[] key = Base64.getDecoder().decode("wLd39ypA5uOeydsszUh3f6OXijomn+VVIpFlaDkF86w=");

        String token = Jwts.builder()
                // id del usuario
                .subject(String.valueOf(user.getId()))
                // La clave secreta para firmar el token y saber que es nuestro cuando lleguen las peticiones del frontend
                .signWith(Keys.hmacShaKeyFor(key))
                // Fecha emisión del token
                .issuedAt(issuedDate)
                // Fecha de expiración del token
                .expiration(expirationDate)
                // información personalizada: rol, username, email...
                .claim("role", user.getRole())
                .claim("email", user.getEmail())
                // Construye el token
                .compact();
        return ResponseEntity.ok(new Token(token)).getBody();


    }
    @PutMapping("users/account")
    public User update(@RequestBody User user){
        SecurityUtils.getCurrentUser().ifPresent(currentUser -> {

            if (currentUser.getRole() == Role.ADMIN || Objects.equals(currentUser.getId(), user.getId())){

                this.userRepository.save(user);
            } else {
                throw new RuntimeException("No tiene permisos necesarios, no se puede actualizar.");
            }
        });

        return user;



    }

    @PostMapping("users/account/avatar")
    public User uploadAvatar(@RequestParam(value = "photo", required = false) MultipartFile file)
    {

        User user = SecurityUtils.getCurrentUser().orElseThrow();
        if (file != null){
            String fileName = fileService.store(file);
            user.setImgUser(fileName);
            this.userRepository.save(user);

        }
        return user;

    }

    @PutMapping("user/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user){
        Optional<User> userOtp = userRepository.findById(id);

        if (userOtp.isEmpty()){ // no existe error404 not faund
            return ResponseEntity.notFound().build();
        }

        User usuariosFromDB = userOtp.get();
        // faltan mas atributos
        return ResponseEntity.ok(userRepository.save(usuariosFromDB));
    }
    @DeleteMapping("user/id")
    private ResponseEntity<Void> deleteById(@PathVariable Long id){

        userRepository.deleteById(id);
        return ResponseEntity.noContent().build(); //204
    }
}

package com.BiteBooking.backend.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor
@Configuration

public class SecurityConfig {
private final RequestJWTFilter requestJWTFilter;
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
    /*return http
.csrf(csrf -> csrf.disable())
.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
.requestMatchers("/users/login").permitAll()
.requestMatchers("/users/register").permitAll()
.anyRequest().authenticated()
).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
.build();*/

        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeHttpRequests()
                .requestMatchers("users/login", "users/register","files/**", "users/account/avatar").permitAll()


                .requestMatchers(HttpMethod.GET).permitAll()

                .requestMatchers("/users/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/users/account/avatar").permitAll()
                .requestMatchers(HttpMethod.PUT , "/users/account/avatar").permitAll()

                .requestMatchers(HttpMethod.POST, "menus", "dishes", "restaurant").hasAnyAuthority("ADMIN", "RESTAURANT")
                .requestMatchers(HttpMethod.PUT, "menus", "dishes", "restaurant").hasAnyAuthority("ADMIN", "RESTAURANT")
                .requestMatchers(HttpMethod.DELETE, "menus", "dishes").hasAnyAuthority("ADMIN", "RESTAURANT")

                .anyRequest().authenticated();

        http.addFilterBefore(requestJWTFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

package com.omar26begue.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v3/api-docs", "/v3/api-docs/**", "/swagger-ui.html",
                                "/swagger-ui/**", "/swagger-resources/**", "/webjars/**")
                        .permitAll()

                        .requestMatchers(request -> "GET".equals(request.getMethod()) &&
                                request.getRequestURI().startsWith("/rooms"))
                        .permitAll()

                        .requestMatchers(request -> "POST".equals(request.getMethod()) &&
                                request.getRequestURI().startsWith("/rooms"))
                        .authenticated()

                        .requestMatchers(request -> "GET".equals(request.getMethod()) &&
                                request.getRequestURI().startsWith("/bookings"))
                        .permitAll()

                        .requestMatchers(request -> ("POST".equals(request.getMethod()) ||
                                "DELETE".equals(request.getMethod())) &&
                                request.getRequestURI().startsWith("/bookings"))
                        .authenticated()

                        .anyRequest().authenticated())
                .httpBasic(basic -> basic.realmName("Demo Booking API"));
        return http.build();
    }
}
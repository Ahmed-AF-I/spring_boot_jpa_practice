package com.workshop.bouali.config;

import com.workshop.bouali.config.filter.RateLimitingFilter;
import com.workshop.bouali.repositories.employeerepo.EmployeeRepository;
import com.workshop.bouali.users.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final EmployeeRepository employeeRepository;
    private final RateLimitingFilter rateLimitingFilter;


    @Value("${app.security.crypto.argon2.parallelism:1}")
    private int parallelism;

    @Value("${app.security.crypto.argon2.memory:16384}")
    private int memory;

    @Value("${app.security.crypto.argon2.iterations:2}")
    int iterations;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> employeeRepository.findByEmail(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("user is invalid"));
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http
    ) {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(rateLimitingFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(
                authorize -> authorize
                        .requestMatchers("/api/address/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/api/employees/**").hasAuthority("ROLE_ADMIN")

                        .requestMatchers("/api/department/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                        .requestMatchers("/api/missions/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                        .anyRequest().authenticated()
        )
                .headers(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder(
                16,
                32,
                parallelism,
                memory,
                iterations
        );
    }
}

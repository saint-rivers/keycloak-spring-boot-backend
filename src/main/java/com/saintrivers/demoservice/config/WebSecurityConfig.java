package com.saintrivers.demoservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .cors().and()
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/api/v1/test/public").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer().jwt();

        return http.build();
    }
}

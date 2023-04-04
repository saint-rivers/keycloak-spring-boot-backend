package com.saintrivers.demoservice.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/test")
@Slf4j
@CrossOrigin
class TestController {

    record MessageResponse(
            String message,
            String timestamp,
            @JsonInclude(JsonInclude.Include.NON_NULL) Object principal) {
    }

    @GetMapping("/public")
    public MessageResponse testPublicRoute() {
        return new MessageResponse("public route", LocalDateTime.now().toString(), null);
    }

    @GetMapping("/private")
    public MessageResponse testPrivateRoute() {
        var auth = (Jwt)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(auth.toString());
        return new MessageResponse("accessing private route", LocalDateTime.now().toString(), auth.getClaimAsString("email"));
    }
}

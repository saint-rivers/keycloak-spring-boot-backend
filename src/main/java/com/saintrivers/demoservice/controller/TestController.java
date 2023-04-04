package com.saintrivers.demoservice.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
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
    public MessageResponse testPrivateRoute(@AuthenticationPrincipal Principal principal) {
        return new MessageResponse("accessing private route", LocalDateTime.now().toString(), principal);
    }
}

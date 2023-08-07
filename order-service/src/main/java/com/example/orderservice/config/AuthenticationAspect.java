package com.example.orderservice.config;

import com.example.orderservice.controllers.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class AuthenticationAspect {

    private final CustomContextHolder contextHolder;
    private final HttpServletRequest request;


    @Before("@annotation(RequiresAuthentication)")
    public void beforeRequiresAuthentication() {
        if (!contextHolder.isAuthenticated()) {
            log.error("{} - {} - {} - {} - {}", request.getMethod(), request.getRequestURI(), contextHolder.getCorrelationId(), contextHolder.getUsername(), null);
            throw new AuthException(HttpStatus.UNAUTHORIZED);
        }
    }
}


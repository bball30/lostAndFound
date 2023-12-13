package ru.itmo.lostandfound.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.lostandfound.messages.auth.AuthRequest;
import ru.itmo.lostandfound.messages.auth.AuthResponse;
import ru.itmo.lostandfound.services.AuthService;

/**
 * Контроллер с эндпоинтами авторизации
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authorizationService;

    /**
     * Эндпоинт для запроса на авторизацию
     *
     * @param authRequest тело запроса с логином и паролем
     * @return <code>ResponseEntity</code> с кодом ответа и jwt-токеном
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authUser(
            @RequestBody @Valid AuthRequest authRequest
    ) {
        AuthResponse response = authorizationService.authUser(authRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

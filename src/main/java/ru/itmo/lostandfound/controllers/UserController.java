package ru.itmo.lostandfound.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.lostandfound.messages.dtos.UserDto;
import ru.itmo.lostandfound.services.UserService;

/**
 * Контроллер с эндпоинтами на работу с пользователями
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    /**
     * Получение пользователя по токену в заголовке авторизации
     *
     * @param authentication сущность с информацией об аутентификации пользователя
     * @return <code>ResponseEntity</code> с кодом ответа и телом, содержащим информацию о найденном пользователе
     */
    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserByToken(Authentication authentication) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getUser(authentication.getName()));
    }
}

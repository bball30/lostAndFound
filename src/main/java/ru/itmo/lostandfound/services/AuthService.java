package ru.itmo.lostandfound.services;

import ru.itmo.lostandfound.messages.auth.AuthRequest;
import ru.itmo.lostandfound.messages.auth.AuthResponse;

public interface AuthService {
    AuthResponse authUser(AuthRequest authRequest);
}

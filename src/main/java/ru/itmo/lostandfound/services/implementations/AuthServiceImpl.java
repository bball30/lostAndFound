package ru.itmo.lostandfound.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.itmo.lostandfound.messages.auth.AuthRequest;
import ru.itmo.lostandfound.messages.auth.AuthResponse;
import ru.itmo.lostandfound.model.User;
import ru.itmo.lostandfound.security.JwtUtil;
import ru.itmo.lostandfound.services.AuthService;
import ru.itmo.lostandfound.util.resolvers.UserResolveService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserResolveService userResolveService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthResponse authUser(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword()));
        User user = userResolveService.resolve(authRequest.getLogin());
        return generateUserAccessTokens(user);
    }

    private AuthResponse generateUserAccessTokens(User user) {
        return new AuthResponse(jwtUtil.generateAccessToken(user.getLogin()));
    }
}

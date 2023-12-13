package ru.itmo.lostandfound.util.resolvers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.lostandfound.exceptions.NoSuchUserException;
import ru.itmo.lostandfound.model.User;
import ru.itmo.lostandfound.repositories.UserRepository;

/**
 * Сервис для получения сущностей пользователей по их логину
 */
@Service
@RequiredArgsConstructor
public class UserResolveService implements ResolveService<String, User> {

    private final UserRepository userRepository;

    @Override
    public User resolve(String login) {
        return userRepository.findByLogin(login).orElseThrow(() -> new NoSuchUserException(login));
    }
}

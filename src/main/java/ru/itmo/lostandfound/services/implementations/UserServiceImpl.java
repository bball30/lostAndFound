package ru.itmo.lostandfound.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.lostandfound.messages.dtos.UserDto;
import ru.itmo.lostandfound.util.converters.UserToUserDtoConverter;
import ru.itmo.lostandfound.util.resolvers.UserResolveService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements ru.itmo.lostandfound.services.UserService {
    private final UserResolveService userResolveService;
    private final UserToUserDtoConverter userToUserDtoConverter;

    @Override
    public UserDto getUser(String login) {
        return userToUserDtoConverter.convert(userResolveService.resolve(login));
    }
}

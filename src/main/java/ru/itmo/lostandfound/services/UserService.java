package ru.itmo.lostandfound.services;

import ru.itmo.lostandfound.messages.dtos.UserDto;

public interface UserService {
    UserDto getUser(String login);
}

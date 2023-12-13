package ru.itmo.lostandfound.util.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itmo.lostandfound.messages.dtos.UserDto;
import ru.itmo.lostandfound.model.User;

/**
 * Конвертер, ставящий в соответствие сущности User сущность UserDto
 */
@Component
@RequiredArgsConstructor
public class UserToUserDtoConverter implements Converter<User, UserDto> {

    private final CampusToCampusDtoConverter campusToCampusDtoConverter;

    @Override
    public UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setLogin(user.getLogin());
        userDto.setCampus(campusToCampusDtoConverter.convert(user.getCampus()));
        userDto.setRole(user.getRole());

        return userDto;
    }
}

package ru.itmo.lostandfound.messages.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.itmo.lostandfound.model.Role;

@Getter
@Setter
public class UserDto {
    @JsonProperty("login")
    private String login;

    @JsonProperty("role")
    private Role role;

    @JsonProperty("campus")
    private CampusDto campus;
}

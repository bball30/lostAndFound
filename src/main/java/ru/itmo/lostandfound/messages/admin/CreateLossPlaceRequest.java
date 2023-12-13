package ru.itmo.lostandfound.messages.admin;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLossPlaceRequest {
    @NotBlank
    private String name;
}

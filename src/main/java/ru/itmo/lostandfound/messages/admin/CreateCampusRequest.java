package ru.itmo.lostandfound.messages.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCampusRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String city;

    @NotBlank
    @JsonProperty("short_name")
    private String shortName;

    @NotBlank
    private String address;

    @NotBlank
    @JsonProperty("working_hours")
    private String workingHours;

    @NotBlank
    @JsonProperty("working_days")
    private String workingDays;
}

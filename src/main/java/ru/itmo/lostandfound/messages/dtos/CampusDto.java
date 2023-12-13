package ru.itmo.lostandfound.messages.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampusDto {
    private String name;

    private String address;

    @JsonProperty("working_hours")
    private String workingHours;

    @JsonProperty("working_days")
    private String workingDays;
}

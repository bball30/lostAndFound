package ru.itmo.lostandfound.messages.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ItemStatusChangeDto {
    private String id;

    private ItemDto item;

    @JsonProperty("old_status")
    private String oldStatus;

    @JsonProperty("new_status")
    private String newStatus;

    @JsonProperty("change_date")
    private Timestamp changeDate;

    private UserDto user;
}

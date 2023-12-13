package ru.itmo.lostandfound.messages.info;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetCampusesResponse {
    private List<String> campuses;
}

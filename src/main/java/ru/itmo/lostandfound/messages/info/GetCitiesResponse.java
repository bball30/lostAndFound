package ru.itmo.lostandfound.messages.info;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetCitiesResponse {
    private List<String> cities;
}

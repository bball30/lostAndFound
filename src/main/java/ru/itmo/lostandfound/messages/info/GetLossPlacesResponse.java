package ru.itmo.lostandfound.messages.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetLossPlacesResponse {
    @JsonProperty(value = "lost_places")
    private List<String> lostPlaces;
}

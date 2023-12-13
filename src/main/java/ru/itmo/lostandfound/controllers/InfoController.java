package ru.itmo.lostandfound.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.lostandfound.messages.info.GetCategoriesResponse;
import ru.itmo.lostandfound.messages.info.GetCitiesResponse;
import ru.itmo.lostandfound.messages.info.GetLossPlacesResponse;
import ru.itmo.lostandfound.messages.info.GetCampusesResponse;
import ru.itmo.lostandfound.services.InfoService;

@RestController
@RequestMapping("/api/info")
@RequiredArgsConstructor
public class InfoController {

    private final InfoService infoService;

    @GetMapping("/campuses")
    public ResponseEntity<GetCampusesResponse> suggestOffices() {
        return ResponseEntity.status(HttpStatus.OK).body(infoService.getCampuses());
    }

    @GetMapping("/categories")
    public ResponseEntity<GetCategoriesResponse> getCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(infoService.getCategories());
    }

    @GetMapping("/loss_places")
    public ResponseEntity<GetLossPlacesResponse> getLossPlaces() {
        return ResponseEntity.status(HttpStatus.OK).body(infoService.getLossPlaces());
    }
}

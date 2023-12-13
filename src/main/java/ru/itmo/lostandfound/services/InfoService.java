package ru.itmo.lostandfound.services;

import ru.itmo.lostandfound.messages.info.GetCategoriesResponse;
import ru.itmo.lostandfound.messages.info.GetCitiesResponse;
import ru.itmo.lostandfound.messages.info.GetLossPlacesResponse;
import ru.itmo.lostandfound.messages.info.GetCampusesResponse;

public interface InfoService {
    GetCampusesResponse getCampuses();

    GetCategoriesResponse getCategories();

    GetLossPlacesResponse getLossPlaces();
}
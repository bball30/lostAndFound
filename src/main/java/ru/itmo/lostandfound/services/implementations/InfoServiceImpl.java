package ru.itmo.lostandfound.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.lostandfound.messages.info.GetCategoriesResponse;
import ru.itmo.lostandfound.messages.info.GetLossPlacesResponse;
import ru.itmo.lostandfound.messages.info.GetCampusesResponse;
import ru.itmo.lostandfound.model.Campus;
import ru.itmo.lostandfound.model.Category;
import ru.itmo.lostandfound.model.LossPlace;
import ru.itmo.lostandfound.repositories.CategoryRepository;
import ru.itmo.lostandfound.repositories.LossPlaceRepository;
import ru.itmo.lostandfound.repositories.CampusRepository;
import ru.itmo.lostandfound.services.InfoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService {
    
    private final CategoryRepository categoryRepository;
    private final CampusRepository campusRepository;
    private final LossPlaceRepository lossPlaceRepository;

    @Override
    public GetCampusesResponse getCampuses() {
        List<Campus> campuses = campusRepository.findAll();
        GetCampusesResponse getCampusesResponse = new GetCampusesResponse();
        getCampusesResponse.setCampuses(campuses.stream().map(Campus::getName).toList());
        return getCampusesResponse;
    }

    @Override
    public GetCategoriesResponse getCategories() {
        List<Category> categories = categoryRepository.findAll();
        GetCategoriesResponse getCategoriesResponse = new GetCategoriesResponse();
        getCategoriesResponse.setCategories(categories.stream().map(Category::getName).toList());
        return getCategoriesResponse;
    }

    @Override
    public GetLossPlacesResponse getLossPlaces() {
        List<LossPlace> lossPlaces = lossPlaceRepository.findAll();
        GetLossPlacesResponse getLossPlacesResponse = new GetLossPlacesResponse();
        getLossPlacesResponse.setLostPlaces(lossPlaces.stream().map(LossPlace::getName).toList());
        return getLossPlacesResponse;
    }
}

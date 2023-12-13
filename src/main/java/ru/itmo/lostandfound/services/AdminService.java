package ru.itmo.lostandfound.services;

import ru.itmo.lostandfound.exceptions.*;
import ru.itmo.lostandfound.messages.admin.*;

public interface AdminService {
    void createCampus(CreateCampusRequest createCampusRequest) throws CampusAlreadyExistsException;
    void deleteCampus(String campusName) throws NoSuchCampusException;

    void createCategory(CreateCategoryRequest createCategoryRequest) throws CategoryAlreadyExistsException;
    void deleteCategory(String categoryName) throws NoSuchCategoryException;

    void createLossPlace(CreateLossPlaceRequest createLossPlaceRequest) throws LossPlaceAlreadyExistsException;
    void deleteLossPlace(String lossPlaceName) throws NoSuchLossPlaceException;
}

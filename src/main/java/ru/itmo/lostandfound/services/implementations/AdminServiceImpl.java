package ru.itmo.lostandfound.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.lostandfound.exceptions.*;
import ru.itmo.lostandfound.messages.admin.*;
import ru.itmo.lostandfound.model.*;
import ru.itmo.lostandfound.repositories.*;
import ru.itmo.lostandfound.services.AdminService;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final CampusRepository campusRepository;
    private final CategoryRepository categoryRepository;
    private final LossPlaceRepository lossPlaceRepository;

    @Override
    @Transactional
    public void createCampus(CreateCampusRequest createCampusRequest) throws CampusAlreadyExistsException {
        if (campusRepository.existsByName(createCampusRequest.getName())) {
            throw new CampusAlreadyExistsException(createCampusRequest.getName());
        }
        Campus campus = new Campus(null,
                createCampusRequest.getName(), createCampusRequest.getShortName(),
                createCampusRequest.getAddress(), createCampusRequest.getWorkingHours(),
                createCampusRequest.getWorkingDays());
        campusRepository.save(campus);
    }

    @Override
    @Transactional
    public void deleteCampus(String campusName) throws NoSuchCampusException {
        if (!campusRepository.existsByName(campusName)) {
            throw new NoSuchCampusException(campusName);
        }
        campusRepository.deleteByName(campusName);
    }

    @Override
    @Transactional
    public void createCategory(CreateCategoryRequest createCategoryRequest) throws CategoryAlreadyExistsException {
        if (categoryRepository.existsByName(createCategoryRequest.getName())) {
            throw new CategoryAlreadyExistsException(createCategoryRequest.getName());
        }
        Category category = new Category(null, createCategoryRequest.getName());
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void deleteCategory(String categoryName) throws NoSuchCategoryException {
        if (!categoryRepository.existsByName(categoryName)) {
            throw new NoSuchCategoryException(categoryName);
        }
        categoryRepository.deleteByName(categoryName);
    }

    @Override
    @Transactional
    public void createLossPlace(CreateLossPlaceRequest createLossPlaceRequest) throws LossPlaceAlreadyExistsException {
        if (lossPlaceRepository.existsByName(createLossPlaceRequest.getName())) {
            throw new LossPlaceAlreadyExistsException(createLossPlaceRequest.getName());
        }
        LossPlace lossPlace = new LossPlace(null, createLossPlaceRequest.getName());
        lossPlaceRepository.save(lossPlace);
    }

    @Override
    @Transactional
    public void deleteLossPlace(String lossPlaceName) throws NoSuchLossPlaceException {
        if (!lossPlaceRepository.existsByName(lossPlaceName)) {
            throw new NoSuchLossPlaceException(lossPlaceName);
        }
        lossPlaceRepository.deleteByName(lossPlaceName);
    }
}

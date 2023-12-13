package ru.itmo.lostandfound.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.itmo.lostandfound.messages.admin.*;
import ru.itmo.lostandfound.services.AdminService;

/**
 * Контроллер с эндпоинтами для администратора
 */
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Validated
public class AdminController {

    private final AdminService adminService;

    /**
     * Добавление нового корпуса в базу данных
     *
     * @param createCampusRequest сущность с данными о новом корпусе
     * @return <code>ResponseEntity</code> с кодом 200, если добавление прошло успешно
     */
    @PostMapping("/campus")
    public ResponseEntity<?> createCampus(@RequestBody @Valid CreateCampusRequest createCampusRequest) {
        adminService.createCampus(createCampusRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/campus")
    public ResponseEntity<?> deleteCampus(@RequestParam(name = "campus_name") @NotBlank String campusName) {
        adminService.deleteCampus(campusName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Добавление новой категории в базу данных
     *
     * @param createCategoryRequest сущность с данными о новой категории
     * @return <code>ResponseEntity</code> с кодом 200, если добавление прошло успешно
     */
    @PostMapping("/category")
    public ResponseEntity<?> createCategory(@RequestBody @Valid CreateCategoryRequest createCategoryRequest) {
        adminService.createCategory(createCategoryRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/category")
    public ResponseEntity<?> deleteCategory(@RequestParam(name = "category_name") @NotBlank String categoryName) {
        adminService.deleteCategory(categoryName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Добавление нового места возможной потери в базу данных
     *
     * @param createLossPlaceRequest сущность с данными о новом месте возможной потери
     * @return <code>ResponseEntity</code> с кодом 200, если добавление прошло успешно
     */
    @PostMapping("/loss_place")
    public ResponseEntity<?> createLossPlace(@RequestBody @Valid CreateLossPlaceRequest createLossPlaceRequest) {
        adminService.createLossPlace(createLossPlaceRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/loss_place")
    public ResponseEntity<?> deleteLossPlace(@RequestParam(name = "loss_place") @NotBlank String lossPlaceName) {
        adminService.deleteLossPlace(lossPlaceName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

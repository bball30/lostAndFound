package ru.itmo.lostandfound.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.itmo.lostandfound.messages.photo.GetPhotoPresignedUrlResponse;
import ru.itmo.lostandfound.messages.photo.UploadPhotoRequest;
import ru.itmo.lostandfound.messages.photo.UploadPhotoResponse;
import ru.itmo.lostandfound.security.CustomUserDetails;
import ru.itmo.lostandfound.services.PhotoService;

/**
 * Контроллер с эндпоинтами для работы с фотографиями
 */
@RestController
@RequestMapping("/api/photo")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    /**
     * Загрузка фотографии в хранилище
     *
     * @param uploadPhotoRequest сущность, содержащая <code>MultipartFile</code> - загружаемый файл
     * @return <code>ResponseEntity</code> с кодом ответа и идентификатором загруженного в хранилище файла
     */
    @PostMapping("")
    public ResponseEntity<UploadPhotoResponse> uploadPhoto(
            @ModelAttribute @Valid UploadPhotoRequest uploadPhotoRequest,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        UploadPhotoResponse response = photoService.uploadPhoto(uploadPhotoRequest, userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Получение ссылки на получение файла из хранилища по его идентификатору
     *
     * @param photoId идентификатор искомого файла
     * @return <code>ResponseEntity</code> с кодом ответа и ссылкой на получение файла
     */
    @GetMapping("/{photo_id}")
    public ResponseEntity<GetPhotoPresignedUrlResponse> getPhotoPresignedUrl(
            @PathVariable("photo_id") String photoId
    ) {
        GetPhotoPresignedUrlResponse response = photoService.getPhotoPresignedUrl(photoId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}

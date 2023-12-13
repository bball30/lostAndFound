package ru.itmo.lostandfound.messages.photo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadPhotoRequest {
    @NotNull
    MultipartFile photo;
}

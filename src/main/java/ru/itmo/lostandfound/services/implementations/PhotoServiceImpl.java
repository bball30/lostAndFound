package ru.itmo.lostandfound.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.itmo.lostandfound.exceptions.NoSuchPhotoException;
import ru.itmo.lostandfound.exceptions.ObjectStorageException;
import ru.itmo.lostandfound.exceptions.UploadPhotoException;
import ru.itmo.lostandfound.messages.photo.GetPhotoPresignedUrlResponse;
import ru.itmo.lostandfound.messages.photo.UploadPhotoRequest;
import ru.itmo.lostandfound.messages.photo.UploadPhotoResponse;
import ru.itmo.lostandfound.model.Photo;
import ru.itmo.lostandfound.photo.AwsUtil;
import ru.itmo.lostandfound.repositories.PhotoRepository;
import ru.itmo.lostandfound.repositories.UserRepository;
import ru.itmo.lostandfound.security.CustomUserDetails;
import ru.itmo.lostandfound.services.PhotoService;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;
    private final AwsUtil awsUtil;

    @Override
    @Transactional
    public UploadPhotoResponse uploadPhoto(UploadPhotoRequest request, CustomUserDetails userDetails) {
        MultipartFile file = request.getPhoto();
        if (file.getContentType() == null || file.getSize() == 0) {
            throw new UploadPhotoException(file.getOriginalFilename());
        }
        if (!file.getContentType().startsWith("image/")) {
            throw new UploadPhotoException(file.getOriginalFilename() + " has unsupported type " + file.getContentType());
        }
        UploadPhotoResponse response = new UploadPhotoResponse();
        String key = UUID.randomUUID().toString();
        try {
            awsUtil.uploadPhoto(key, file);
        } catch (IOException e) {
            throw new UploadPhotoException(file.getOriginalFilename());
        } catch (ObjectStorageException e) {
            throw e;
        } catch (Exception e) {
            throw new ObjectStorageException(file.getOriginalFilename());
        }

        Photo photo = new Photo(
                key,
                Timestamp.from(Instant.now()),
                userRepository.findById(userDetails.getId()).orElseThrow(),
                null
        );
        photoRepository.save(photo);

        response.setId(key);
        return response;
    }

    @Override
    public GetPhotoPresignedUrlResponse getPhotoPresignedUrl(String photoId) {
        if (!photoRepository.existsById(photoId)) {
            throw new NoSuchPhotoException(photoId);
        }

        GetPhotoPresignedUrlResponse response = new GetPhotoPresignedUrlResponse();
        try {
            response.setUrl(awsUtil.downloadPhotoUrl(photoId).toString());
        } catch (ObjectStorageException e) {
            throw e;
        } catch (Exception e) {
            throw new ObjectStorageException(photoId);
        }
        return response;
    }
}

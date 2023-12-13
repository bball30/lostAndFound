package ru.itmo.lostandfound.services;

import ru.itmo.lostandfound.messages.photo.GetPhotoPresignedUrlResponse;
import ru.itmo.lostandfound.messages.photo.UploadPhotoRequest;
import ru.itmo.lostandfound.messages.photo.UploadPhotoResponse;
import ru.itmo.lostandfound.security.CustomUserDetails;

public interface PhotoService {
    UploadPhotoResponse uploadPhoto(UploadPhotoRequest request, CustomUserDetails userDetails);

    GetPhotoPresignedUrlResponse getPhotoPresignedUrl(String photoId);
}

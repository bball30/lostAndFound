package ru.itmo.lostandfound.messages.photo;

import lombok.Data;

@Data
public class GetPhotoPresignedUrlResponse {
    String url;
}

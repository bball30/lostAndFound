package ru.itmo.lostandfound.exceptions;

/**
 * Исключение, сигнализирующее о том, что полученное изображение некорректно
 */
public class UploadPhotoException extends RuntimeException {
    public UploadPhotoException(String message) {
        super(message);
    }
}

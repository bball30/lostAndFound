package ru.itmo.lostandfound.exceptions;

/**
 * Исключение, сигнализирующее о принадлежности указанной фотографии к другому пользователю (хранителю)
 */
public class PhotoBelongsAnotherUserException extends RuntimeException {
    public PhotoBelongsAnotherUserException(String message) {
        super(message);
    }
}

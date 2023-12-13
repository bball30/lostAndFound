package ru.itmo.lostandfound.exceptions;

/**
 * Исключение, сигнализирующее о том, что фото с указанным <code>id</code> не было найдено в хранилище
 */
public class NoSuchPhotoException extends RuntimeException {
    public NoSuchPhotoException(String message) {
        super(message);
    }
}

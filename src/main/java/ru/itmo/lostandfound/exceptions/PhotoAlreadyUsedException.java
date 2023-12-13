package ru.itmo.lostandfound.exceptions;

/**
 * Исключение, сигнализирующее о привязанности указанной фотографии к другой потеряшке
 */
public class PhotoAlreadyUsedException extends RuntimeException {
    public PhotoAlreadyUsedException(String message) {
        super(message);
    }
}

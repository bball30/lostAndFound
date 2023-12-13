package ru.itmo.lostandfound.exceptions;

/**
 * Исключение, сигнализирующее о том, что хранилище S3 в данный момент недоступно или произошла ошибка
 */
public class ObjectStorageException extends RuntimeException {
    public ObjectStorageException(String message) {
        super(message);
    }
}

package ru.itmo.lostandfound.exceptions;

/**
 * Исключение, сигнализирующее о наличии офиса с указанным при создании именем
 */
public class CampusAlreadyExistsException extends RuntimeException {
    public CampusAlreadyExistsException(String message) {
        super(message);
    }
}

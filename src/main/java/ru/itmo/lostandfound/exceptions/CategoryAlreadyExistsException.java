package ru.itmo.lostandfound.exceptions;

/**
 * Исключение, сигнализирующее о наличии категории с указанным при создании названием
 */
public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException(String message) {
        super(message);
    }
}

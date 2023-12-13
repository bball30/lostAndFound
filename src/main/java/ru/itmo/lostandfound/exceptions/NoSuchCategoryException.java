package ru.itmo.lostandfound.exceptions;

/**
 * Исключение, сигнализирующее о том, что запрашиваемая категория не была найдена в базе
 */
public class NoSuchCategoryException extends RuntimeException {
    public NoSuchCategoryException(String message) {
        super(message);
    }
}

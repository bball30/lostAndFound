package ru.itmo.lostandfound.exceptions;

/**
 * Исключение, сигнализирующее о наличии места потери, которое вы пытаетесь добавить
 */
public class LossPlaceAlreadyExistsException extends RuntimeException {
    public LossPlaceAlreadyExistsException(String message) {
        super(message);
    }
}

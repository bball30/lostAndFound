package ru.itmo.lostandfound.exceptions;

/**
 * Исключение, сигнализирующее о попытке смены статуса (<code>ItemStatus</code>) на тот же самый
 * <p>
 * @see ru.itmo.lostandfound.model.ItemStatus
 */
public class ItemStatusConflictException extends RuntimeException {
    public ItemStatusConflictException(String message) {
        super(message);
    }
}

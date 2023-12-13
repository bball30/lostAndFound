package ru.itmo.lostandfound.exceptions;

/**
 * Исключение, сигнализирующее о некорректном статусе <code>ItemStatus</code>
 * <p>
 * @see ru.itmo.lostandfound.model.ItemStatus
 */
public class IllegalItemStatusException extends RuntimeException {
    public IllegalItemStatusException(String message) {
        super(message);
    }
}

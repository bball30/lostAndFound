package ru.itmo.lostandfound.exceptions;

/**
 * Исключение, сигнализирующее о том, что <code>LossPlace</code> с указанным <code>name</code> не был найден
 */
public class NoSuchLossPlaceException extends RuntimeException {
    public NoSuchLossPlaceException(String message) {
        super(message);
    }
}

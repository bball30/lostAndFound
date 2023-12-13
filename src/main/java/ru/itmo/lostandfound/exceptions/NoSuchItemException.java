package ru.itmo.lostandfound.exceptions;

/**
 * Исключение, сигнализирующее о том, что <code>Item</code> с указанным <code>id</code> не был найден
 */
public class NoSuchItemException extends RuntimeException {
    public NoSuchItemException(String message) {
        super(message);
    }
}

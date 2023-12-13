package ru.itmo.lostandfound.exceptions;

/**
 * Исключение, сигнализирующее о том, что <code>User</code> с указанным <code>login</code> не был найден
 */
public class NoSuchUserException extends RuntimeException {
    public NoSuchUserException(String message) {
        super(message);
    }
}
package ru.itmo.lostandfound.exceptions;

public class IllegalLossTimeException extends RuntimeException {
    public IllegalLossTimeException(String message) {
        super(message);
    }
}

package ru.itmo.lostandfound.exceptions;


public class NoSuchCampusException extends RuntimeException {
    public NoSuchCampusException(String message) {
        super(message);
    }
}

package ru.itmo.lostandfound.model;

import lombok.Getter;

@Getter
public enum ItemStatus {
    ACTIVE("Потеряно"),
    LOCKED("Забирают"),
    IN_STORAGE("На складе"),
    FOUND("Найдено");

    private final String tag;

    ItemStatus(String tag) {
        this.tag = tag;
    }
}

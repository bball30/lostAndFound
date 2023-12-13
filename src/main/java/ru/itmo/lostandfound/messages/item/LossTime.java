package ru.itmo.lostandfound.messages.item;

import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Supplier;

@Getter
public enum LossTime {
    TODAY(
            () -> Timestamp.valueOf(LocalDate.now().atStartOfDay()),
            () -> Timestamp.valueOf(LocalDateTime.now())
    ),
    ONE_TWO_DAYS(
            () -> Timestamp.valueOf(LocalDate.now().minusDays(1).atStartOfDay()),
            () -> Timestamp.valueOf(LocalDateTime.now())
    ),
    WEEK(
            () -> Timestamp.valueOf(LocalDate.now().minusWeeks(1).atStartOfDay()),
            () -> Timestamp.valueOf(LocalDateTime.now())
    ),
    MONTH(
            () -> Timestamp.valueOf(LocalDate.now().minusMonths(1).atStartOfDay()),
            () -> Timestamp.valueOf(LocalDate.now().minusWeeks(1).atStartOfDay())
    ),
    HALF_YEAR(
            () -> Timestamp.valueOf(LocalDate.now().minusMonths(6).atStartOfDay()),
            () -> Timestamp.valueOf(LocalDate.now().minusMonths(1).atStartOfDay())
    ),
    FOR_LONG(
            () -> Timestamp.valueOf(LocalDate.now().minusYears(1000).atStartOfDay()),
            () -> Timestamp.valueOf(LocalDate.now().minusMonths(3).atStartOfDay())
    );

    private final Supplier<Timestamp> start;
    private final Supplier<Timestamp> end;

    LossTime(Supplier<Timestamp> start, Supplier<Timestamp> end) {
        this.start = start;
        this.end = end;
    }
}

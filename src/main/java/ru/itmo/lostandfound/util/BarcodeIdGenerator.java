package ru.itmo.lostandfound.util;

import org.springframework.stereotype.Component;
import ru.itmo.lostandfound.model.Campus;

import java.time.Year;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Генератор уникальных идентификаторов для потерянных вещей
 */
@Component
public class BarcodeIdGenerator {

    /**
     * Соответствия офисов к текущим значениям счетчиков вещей в этих офисах
     */
    private final ConcurrentHashMap<String, AtomicLong> numbersByOffices = new ConcurrentHashMap<>();

    /**
     * Сгенерировать идентификатор вещи, формат: {@literal <}КОРОТКОЕ_ИМЯ_ОФИСА{@literal >-<}ГГ{@literal >-<}НОМЕР{@literal >}
     *
     * @param campus сущность офиса, к которому привязана вещь
     * @return сгенерированный идентификатор
     */
    public String generate(Campus campus) {
        AtomicLong number = numbersByOffices.computeIfAbsent(campus.getShortName(), (id) -> new AtomicLong(0L));
        long idCounter = number.incrementAndGet();

        return campus.getShortName()
                + "-" + Year.now().getValue() % 100
                + "-" + String.format("%03d", idCounter);
    }
}

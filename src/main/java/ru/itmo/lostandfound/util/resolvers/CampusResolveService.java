package ru.itmo.lostandfound.util.resolvers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.lostandfound.exceptions.NoSuchCampusException;
import ru.itmo.lostandfound.model.Campus;
import ru.itmo.lostandfound.repositories.CampusRepository;

/**
 * Сервис для получения сущностей офисов по их названиям
 */
@Service
@RequiredArgsConstructor
public class CampusResolveService implements ResolveService<String, Campus> {

    private final CampusRepository repository;

    public Campus resolve(String campusName) {
        return repository.findByName(campusName).orElseThrow(() -> new NoSuchCampusException(campusName));
    }
}

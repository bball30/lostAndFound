package ru.itmo.lostandfound.util.resolvers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.lostandfound.exceptions.NoSuchLossPlaceException;
import ru.itmo.lostandfound.model.LossPlace;
import ru.itmo.lostandfound.repositories.LossPlaceRepository;

/**
 * Сервис для получения сущностей мест потери по их названиям
 */
@Service
@RequiredArgsConstructor
public class LossPlaceResolveService implements ResolveService<String, LossPlace> {

    private final LossPlaceRepository repository;

    public LossPlace resolve(String lossPlaceName) {
        return repository.findByName(lossPlaceName).orElseThrow(() -> new NoSuchLossPlaceException(lossPlaceName));
    }
}

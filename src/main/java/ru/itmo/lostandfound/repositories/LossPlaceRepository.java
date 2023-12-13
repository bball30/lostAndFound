package ru.itmo.lostandfound.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.lostandfound.model.LossPlace;

import java.util.Optional;

@Repository
public interface LossPlaceRepository extends JpaRepository<LossPlace, String> {
    Optional<LossPlace> findByName(String lossPlaceName);

    boolean existsByName(String name);

    void deleteByName(String name);
}

package ru.itmo.lostandfound.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.lostandfound.model.Campus;

import java.util.Optional;

@Repository
public interface CampusRepository extends JpaRepository<Campus, String> {
    Optional<Campus> findByName(String officeName);

    boolean existsByName(String name);

    void deleteByName(String name);
}

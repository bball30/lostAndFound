package ru.itmo.lostandfound.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.lostandfound.model.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, String> {
}

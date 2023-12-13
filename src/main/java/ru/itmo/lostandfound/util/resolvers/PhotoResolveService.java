package ru.itmo.lostandfound.util.resolvers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.lostandfound.exceptions.NoSuchPhotoException;
import ru.itmo.lostandfound.model.Photo;
import ru.itmo.lostandfound.repositories.PhotoRepository;

/**
 * Сервис для получения сущностей фотографий по их <code>id</code>
 */
@Service
@RequiredArgsConstructor
public class PhotoResolveService implements ResolveService<String, Photo> {

    private final PhotoRepository photoRepository;

    @Override
    public Photo resolve(String photoId) {
        return photoRepository.findById(photoId).orElseThrow(() -> new NoSuchPhotoException(photoId));
    }
}

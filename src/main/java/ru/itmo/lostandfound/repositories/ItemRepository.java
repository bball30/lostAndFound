package ru.itmo.lostandfound.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.lostandfound.model.Campus;
import ru.itmo.lostandfound.model.Item;
import ru.itmo.lostandfound.model.ItemStatus;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
    boolean existsByPhotoId(String photoId);

    Page<Item> findAllByItemStatusAndUploadDateBetween(ItemStatus itemStatus, Timestamp start, Timestamp end, Pageable pageRequest);

    Page<Item> findAllByCampusAndItemStatusAndUploadDateBetween(Campus campus, ItemStatus itemStatus, Timestamp start, Timestamp end, Pageable pageRequest);

    List<Item> findAllByCampusAndItemStatus(Campus campusName, ItemStatus itemStatus);
}

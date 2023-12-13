package ru.itmo.lostandfound.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itmo.lostandfound.model.Campus;
import ru.itmo.lostandfound.model.ItemStatusChange;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ItemStatusChangeRepository extends JpaRepository<ItemStatusChange, String> {
    List<ItemStatusChange> findAllByItemCampusOrderByChangeDateDesc(Campus campus);

    @Query("""
    SELECT DISTINCT isc FROM ItemStatusChange isc
            WHERE isc.changeDate IN (
                SELECT MAX(innerTable.changeDate) FROM ItemStatusChange innerTable
                WHERE innerTable.item = isc.item
                )
                AND isc.newStatus = 'LOCKED'
                AND isc.changeDate > :dateFrom
                AND isc.changeDate <= :dateTo
""")
    List<ItemStatusChange> findLastLockedChangesBetweenDates(Timestamp dateFrom, Timestamp dateTo);

}

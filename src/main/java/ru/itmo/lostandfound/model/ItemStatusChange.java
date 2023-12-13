package ru.itmo.lostandfound.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item_status_history")
public class ItemStatusChange {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    @Column(name = "old_status")
    @Enumerated(EnumType.STRING)
    private ItemStatus oldStatus;

    @Column(name = "new_status")
    @Enumerated(EnumType.STRING)
    private ItemStatus newStatus;

    @Column(name = "change_date")
    private Timestamp changeDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}

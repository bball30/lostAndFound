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
@Table(name = "photos")
public class Photo {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "upload_date")
    private Timestamp uploadDate;

    @ManyToOne
    @JoinColumn(name = "upload_user_id", referencedColumnName = "id")
    private User user;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;
}

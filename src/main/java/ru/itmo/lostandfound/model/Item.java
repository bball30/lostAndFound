package ru.itmo.lostandfound.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "items")
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "barcode_id", unique = true)
    private String barcodeId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    @OneToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private Photo photo;

    @ManyToOne
    @JoinColumn(name = "campus_id", referencedColumnName = "id")
    private Campus campus;

    @Column(name = "upload_date")
    private Timestamp uploadDate;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "loss_place_id", referencedColumnName = "id")
    private LossPlace lossPlace;
}

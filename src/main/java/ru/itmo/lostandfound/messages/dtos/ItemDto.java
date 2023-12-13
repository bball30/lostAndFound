package ru.itmo.lostandfound.messages.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ItemDto {
    private String id;

    @JsonProperty("barcode_id")
    private String barcodeId;

    @JsonProperty("item_status")
    private String itemStatus;

    @JsonProperty("photo_id")
    private String photoId;

    @JsonProperty(value = "upload_date")
    private Timestamp uploadDate;

    private CampusDto campus;

    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty(value = "loss_place_name")
    private String lossPlaceName;
}

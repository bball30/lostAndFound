package ru.itmo.lostandfound.messages.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CreateItemRequest {
    @NotBlank
    @JsonProperty("photo_id")
    private String photoId;

    @NotBlank
    @Size(max = 100)
    @JsonProperty("campus_name")
    private String campusName;

    @NotNull
    @JsonProperty("upload_date")
    private Timestamp uploadDate;

    @NotBlank
    @Size(max = 50)
    @JsonProperty("category_name")
    private String categoryName;

    @NotBlank
    @Size(max = 50)
    @JsonProperty(value = "loss_place_name")
    private String lossPlaceName;
}

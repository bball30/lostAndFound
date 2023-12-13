package ru.itmo.lostandfound.messages.item;

import lombok.Getter;
import lombok.Setter;
import ru.itmo.lostandfound.messages.dtos.ItemStatusChangeDto;

import java.util.List;

@Getter
@Setter
public class GetBookedItemsResponse {
    private List<ItemStatusChangeDto> itemStatusChanges;
}

package ru.itmo.lostandfound.messages.item;

import lombok.Getter;
import lombok.Setter;
import ru.itmo.lostandfound.messages.dtos.ItemDto;

import java.util.List;

@Getter
@Setter
public class GetItemsResponse {
    private List<ItemDto> items;
}
